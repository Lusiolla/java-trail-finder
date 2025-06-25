package lu.karpychev.trailfinder.dao.impl;

import lombok.RequiredArgsConstructor;
import lu.karpychev.trailfinder.dao.MetadataDao;
import lu.karpychev.trailfinder.model.Metadata;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static lu.karpychev.trailfinder.mapper.MetadataMapper.*;

@Component
@RequiredArgsConstructor
public class MetadataDaoImpl implements MetadataDao {

    private final JdbcTemplate jdbcTemplate;


    @Override
    public long add(Metadata metadata, UUID track_id) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        long linkId = simpleJdbcInsert
                .withTableName("track_links")
                .usingGeneratedKeyColumns("link_id")
                .executeAndReturnKey(trackLinkToMap(metadata.getLink()))
                .longValue();

        long authorId = simpleJdbcInsert
                .withTableName("track_authors")
                .usingGeneratedKeyColumns("author_id")
                .executeAndReturnKey(trackAuthorToMap(metadata.getAuthor()))
                .longValue();

        return simpleJdbcInsert
                .withTableName("metadata")
                .usingGeneratedKeyColumns("metadata_id")
                .executeAndReturnKey(metadataToMap(metadata.getName(), track_id, linkId, authorId))
                .longValue();

    }
}
