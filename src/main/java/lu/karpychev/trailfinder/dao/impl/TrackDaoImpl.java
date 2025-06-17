package lu.karpychev.trailfinder.dao.impl;

import lombok.RequiredArgsConstructor;
import lu.karpychev.trailfinder.dao.TrackDao;
import lu.karpychev.trailfinder.dto.TrackDto;
import lu.karpychev.trailfinder.model.Track;
import lu.karpychev.trailfinder.model.TrackPoint;
import lu.karpychev.trailfinder.model.TrackSegment;
import org.postgis.LineString;
import org.postgis.PGgeometry;
import org.postgis.Point;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component
@RequiredArgsConstructor
public class TrackDaoImpl implements TrackDao {

    private final JdbcTemplate jdbcTemplate;

    //private final int SRID = 4326;

    private final static String FIND_BY_ID_TRACK = "select * " +
            "from tracks " +
            "where id = ?";

    private final static String FIND_NEAREST_TRACK = "select " +
            "t.id, " +
            "t.name, " +
            "ST_Distance(ST_SetSRID(ST_MakePoint(?, ?), 4326)::geography, t.points::geography) AS distance_meters " +
            "from tracks t " +
            "order by " +
            "distance_meters " +
            "LIMIT 1";


    @Override
    public Track add(Track newTrack) throws FileNotFoundException {
        newTrack.setId(UUID.randomUUID());
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("tracks");
        simpleJdbcInsert.execute(trackToMap(newTrack));
        return findById(newTrack.getId());
    }

    @Override
    public TrackDto findNearestTrack(double lat, double lon) throws FileNotFoundException {
        Optional<TrackDto> track = jdbcTemplate.
                query(FIND_NEAREST_TRACK,
                        (rs, rowNum) -> makeTrackDto(rs), lon, lat)
                .stream()
                .findFirst();

        if (track.isPresent()) {
            return track.get();
        } else {
            throw new FileNotFoundException();
        }
    }


    public Track findById(UUID id) throws FileNotFoundException {
        Optional<Track> track = jdbcTemplate
                .query(FIND_BY_ID_TRACK,
                        (rs, rowNum) -> makeTrack(rs), id)
                .stream()
                .findFirst();

        if (track.isPresent()) {
            return track.get();
        } else {
            throw new FileNotFoundException();
        }
    }


    private Map<String, Object> trackToMap(Track track) {
        Map<String, Object> values = new HashMap<>();
        values.put("id", track.getId());
        values.put("name", track.getType());
        values.put("description", track.getDescription());
        values.put("type", track.getType());
        values.put("points", toLineString(track.getSegments().getPoints()));
        return values;
    }


    private Track makeTrack(ResultSet resultSet) throws SQLException {
        return new Track(
                (UUID) resultSet.getObject("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getString("type"),
                new TrackSegment(fromLineString(
                        (PGgeometry) resultSet.getObject("points")))
        );
    }

    private TrackDto makeTrackDto(ResultSet resultSet) throws SQLException {
        return new TrackDto(
                (UUID) resultSet.getObject("id"),
                resultSet.getString("name"),
                formatDistance(resultSet.getDouble("distance_meters"))
        );
    }

    private LineString toLineString(List<TrackPoint> points) {

        Point[] postgisPoints = points.stream()
                .map(p -> {
                    Point point = new Point(p.getLon(), p.getLat(), p.getElevation());
                    point.dimension = 3;
                    return point;
                })
                .toArray(Point[]::new);

        LineString line = new LineString(postgisPoints);
        line.dimension = 3;
        line.setSrid(4326);
        return line;
    }

    private List<TrackPoint> fromLineString(PGgeometry points) {
        LineString line = (LineString) points.getGeometry();
        return Arrays.stream(line.getPoints())
                .map(p -> new TrackPoint(p.getY(), p.getX(), p.getZ()))
                .toList();
    }

    public static String formatDistance(double meters) {
        if (meters < 1000) {
            return Math.round(meters) + " м";
        } else if (meters < 10000) {
            double km = Math.round(meters / 100.0) / 10.0; // округляем до 1 знака после запятой
            return km + " км";
        } else {
            int km = (int) Math.round(meters / 1000.0);
            return km + " км";
        }
    }


}
