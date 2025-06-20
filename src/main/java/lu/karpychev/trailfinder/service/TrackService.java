package lu.karpychev.trailfinder.service;

import lu.karpychev.trailfinder.dto.TrackDto;
import lu.karpychev.trailfinder.model.GpxFile;
import lu.karpychev.trailfinder.model.Track;

import java.io.FileNotFoundException;
import java.util.UUID;

public interface TrackService {
    UUID createTrackFromFile(GpxFile file) throws FileNotFoundException;

    TrackDto getNearestTrack(double lat, double lon) throws FileNotFoundException;

    GpxFile getGpxFileById(UUID trackId);

    Track getTrackById (UUID tackId) throws FileNotFoundException;
}


