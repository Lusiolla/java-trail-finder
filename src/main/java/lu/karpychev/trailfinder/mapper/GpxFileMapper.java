package lu.karpychev.trailfinder.mapper;

import lu.karpychev.trailfinder.model.*;
import org.postgis.PGgeometry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static lu.karpychev.trailfinder.mapper.LineStringMapper.fromLineString;

public class GpxFileMapper {

    public static Map<String, Object> gpxFileToMap(GpxFile gpxFile, long metadataId) {
        Map<String, Object> values = new HashMap<>();
        values.put("version", gpxFile.getVersion());
        values.put("creator", gpxFile.getCreator());
        values.put("schema_location", gpxFile.getSchemaLocation());
        values.put("metadata_id", metadataId);
        values.put("track_id", gpxFile.getTrack().getId());
        return values;
    }

    public static GpxFile makeGpxFile(ResultSet resultSet) throws SQLException {
        return new GpxFile(
                resultSet.getString("version"),
                resultSet.getString("creator"),
                resultSet.getString("schema_location"),
                new Metadata(
                        resultSet.getString("metadata_name"),
                        new TrackAuthor(resultSet.getString("author_name")),
                        new TrackLink((resultSet.getString("url")))),
                new Track((UUID) resultSet.getObject("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("type"),
                        new TrackSegment(fromLineString(
                                (PGgeometry) resultSet.getObject("points"))))
                );

    }
}
