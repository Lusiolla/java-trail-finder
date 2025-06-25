package lu.karpychev.trailfinder.service.impl;

import lombok.Data;
import lu.karpychev.trailfinder.dao.MetadataDao;
import lu.karpychev.trailfinder.model.Metadata;
import lu.karpychev.trailfinder.service.MetadataService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Component
@Data
public class MetadataServiceImpl implements MetadataService {

    private final MetadataDao metadataDao;

    @Override
    public long addMetadata(Metadata metadata, UUID trackId) {

        return metadataDao.add(metadata, trackId);
    }
}










