package lu.karpychev.trailfinder.dao.impl;

import lombok.RequiredArgsConstructor;
import lu.karpychev.trailfinder.dao.GpxFileDao;
import lu.karpychev.trailfinder.model.GpxFile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.UUID;

import static lu.karpychev.trailfinder.mapper.GpxFileMapper.gpxFileToMap;
import static lu.karpychev.trailfinder.mapper.GpxFileMapper.makeGpxFile;


@Component
@RequiredArgsConstructor
public class GpxFileDaoImpl implements GpxFileDao {
    private final JdbcTemplate jdbcTemplate;

    private final static String FIND_BY_ID_TRACK = "select " +
            "gf.version, " +
            "gf.creator, " +
            "gf.schema_location, " +
            "m.metadata_name, " +
            "a.author_name, " +
            "l.url, " +
            "t.id, " +
            "t.name, " +
            "t.description, " +
            "t.type, " +
            "t.points " +
            "from tracks as t " +
            "inner join gpx_files as gf ON gf.track_id = t.id " +
            "inner join metadata as m ON gf.metadata_id = m.metadata_id " +
            "inner join track_authors as a ON m.author_id = a.author_id " +
            "inner join track_links as l ON m.link_id = l.link_id " +
            "where t.id = ?";

    @Override
    public void add(GpxFile gpxFile, long metadataId) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("gpx_files");
        simpleJdbcInsert.execute(gpxFileToMap(gpxFile, metadataId));
    }

    public Optional<GpxFile> findByIdTrack(UUID id) {
        return jdbcTemplate
                .query(FIND_BY_ID_TRACK,
                        (rs, rowNum) -> makeGpxFile(rs), id)
                .stream()
                .findFirst();
    }
}
