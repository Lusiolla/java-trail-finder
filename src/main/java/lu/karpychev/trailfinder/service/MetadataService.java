package lu.karpychev.trailfinder.service;

import lu.karpychev.trailfinder.model.Metadata;

import java.util.UUID;

public interface MetadataService {

    long addMetadata(Metadata metadata, UUID trackId);

}
