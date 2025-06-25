package lu.karpychev.trailfinder.dao;

import lu.karpychev.trailfinder.model.GpxFile;

import java.util.UUID;

public interface GpxFileDao {

    void add(GpxFile gpxFile, long metadataId);
}
