package lu.karpychev.trailfinder.dao.impl;

import lombok.RequiredArgsConstructor;
import lu.karpychev.trailfinder.dao.GpxFileDao;
import lu.karpychev.trailfinder.model.GpxFile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static lu.karpychev.trailfinder.mapper.GpxFileMapper.gpxFileToMap;


@Component
@RequiredArgsConstructor
public class GpxFileImpl implements GpxFileDao {
    private final JdbcTemplate jdbcTemplate;

    private final static String FIND_BY_ID_TRACK = "select " +
            "gf.version, +" +
            "gf.creator, " +
            "gf.schema_location, " +
            "m.metadata_name, " +
            "a.author_name " +
            "l.url, " +
            "t.id, " +
            "t.name, " +
            "t.description, " +
            "t.type, " +
            "t.points, " +
            "from metadata as m " +
            "from track_authors as a " +
            "from track_links as l " +
            "from tracks as t " +
            "inner join gpx_files as gf ON gf.metadata_id = m.metadata_id " +
            "inner join metadata as m ON m.author_id = a.author_id " +
            "inner join metadata as m ON m.link_id = l.link_id " +
            "inner join gpx_files as gf ON gf.tack_id = t.id";

    @Override
    public void add(GpxFile gpxFile, long metadataId) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("tracks");
        simpleJdbcInsert.execute(gpxFileToMap(gpxFile, metadataId));

    }
}
