package lu.karpychev.trailfinder.dao.impl;

import lombok.RequiredArgsConstructor;
import lu.karpychev.trailfinder.dao.TrackDao;
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

    private final static String FIND_BY_ID_TRACK = "select * " +
            "from tracks " +
            "where id = ?";


    @Override
    public Track add(Track newTrack) throws FileNotFoundException {
        newTrack.setId(UUID.randomUUID());
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("tracks");
        simpleJdbcInsert.execute(trackToMap(newTrack));
        return findById(newTrack.getId());
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
}
