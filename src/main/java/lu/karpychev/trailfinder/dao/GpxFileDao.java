package lu.karpychev.trailfinder.dao;

import lu.karpychev.trailfinder.model.GpxFile;

import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.UUID;

public interface GpxFileDao {

    void add(GpxFile gpxFile, long metadataId);

    Optional<GpxFile> findByIdTrack(UUID id);
}
