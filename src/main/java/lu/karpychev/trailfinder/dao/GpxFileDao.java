package lu.karpychev.trailfinder.dao;

import lu.karpychev.trailfinder.model.GpxFile;

import java.io.FileNotFoundException;
import java.util.UUID;

public interface GpxFileDao {

    void add(GpxFile gpxFile, long metadataId);

    GpxFile findByIdTrack(UUID id) throws FileNotFoundException;
}
