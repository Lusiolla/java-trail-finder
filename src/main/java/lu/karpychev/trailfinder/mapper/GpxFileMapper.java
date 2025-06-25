package lu.karpychev.trailfinder.mapper;

import lu.karpychev.trailfinder.model.GpxFile;

import java.util.HashMap;
import java.util.Map;

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
}
