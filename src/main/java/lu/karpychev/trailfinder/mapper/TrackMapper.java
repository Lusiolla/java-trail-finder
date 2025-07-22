package lu.karpychev.trailfinder.mapper;

import lu.karpychev.trailfinder.dto.TrackDto;
import lu.karpychev.trailfinder.model.Track;
import lu.karpychev.trailfinder.model.TrackSegment;
import org.postgis.PGgeometry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static lu.karpychev.trailfinder.mapper.LineStringMapper.fromLineString;
import static lu.karpychev.trailfinder.mapper.LineStringMapper.toLineString;

public class TrackMapper {

    public static Map<String, Object> trackToMap(Track track) {
        Map<String, Object> values = new HashMap<>();
        values.put("id", track.getId());
        values.put("name", track.getName());
        values.put("description", track.getDescription());
        values.put("type", track.getType());
        values.put("points", toLineString(track.getSegments().getPoints()));
        return values;
    }


    public static Track makeTrack(ResultSet resultSet) throws SQLException {
        return new Track(
                (UUID) resultSet.getObject("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getString("type"),
                new TrackSegment(fromLineString(
                        (PGgeometry) resultSet.getObject("points")))
        );
    }

    public static TrackDto makeTrackDto(ResultSet resultSet) throws SQLException {
        return new TrackDto(
                (UUID) resultSet.getObject("id"),
                resultSet.getString("name"),
                formatDistance(resultSet.getDouble("distance_meters"))
        );
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
