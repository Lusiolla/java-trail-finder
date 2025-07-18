package lu.karpychev.trailfinder.mapper;


import lu.karpychev.trailfinder.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class MetadataMapper {

    public static Map<String, Object> metadataToMap(String metadataName, UUID trackId, long authorId, long linkId) {
        Map<String, Object> values = new HashMap<>();
        values.put("track_id", trackId);
        values.put("metadata_name", metadataName);
        values.put("author_id", authorId);
        values.put("link_id", linkId);
        return values;
    }

    public static Map<String, Object> trackLinkToMap(TrackLink link) {
        Map<String, Object> values = new HashMap<>();
        if (link == null) {
            values.put("url", null);
        } else {
            values.put("url", link.getURL());
        }
        return values;
    }

    public static Map<String, Object> trackAuthorToMap(TrackAuthor author) {
        Map<String, Object> values = new HashMap<>();
        values.put("author_name", author.getName());
        return values;
    }

}
