package lu.karpychev.trailfinder.dao;

import lu.karpychev.trailfinder.model.Metadata;

import java.util.UUID;

public interface MetadataDao {

    long add(Metadata metadata, UUID track_id);
}
