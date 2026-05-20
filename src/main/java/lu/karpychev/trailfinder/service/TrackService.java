package lu.karpychev.trailfinder.service;

import lu.karpychev.trailfinder.dto.TrackDto;
import lu.karpychev.trailfinder.model.GpxFile;
import lu.karpychev.trailfinder.model.Track;

import java.io.FileNotFoundException;
import java.util.UUID;

public interface TrackService {
    Track createTrack(Track newTrack) throws FileNotFoundException;

    TrackDto getNearestTrack(double lat, double lon) throws FileNotFoundException;

    Track getTrackById (UUID tackId);
}


