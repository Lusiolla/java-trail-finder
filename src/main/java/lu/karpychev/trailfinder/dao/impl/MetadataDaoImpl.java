package lu.karpychev.trailfinder.dao.impl;

import lombok.RequiredArgsConstructor;
import lu.karpychev.trailfinder.dao.MetadataDao;
import lu.karpychev.trailfinder.model.Metadata;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MetadataDaoImpl implements MetadataDao {

    private final JdbcTemplate jdbcTemplate;


    @Override
    public void add(Metadata metadata, UUID track_id) {

    }
}
