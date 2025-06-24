package lu.karpychev.trailfinder.service;

import lu.karpychev.trailfinder.model.Metadata;

import java.util.UUID;

public interface MetadataService {

    void addMetadata (Metadata metadata, UUID track_id);
}
