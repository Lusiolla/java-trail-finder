package lu.karpychev.trailfinder.service;

import lu.karpychev.trailfinder.dto.TrackDto;
import lu.karpychev.trailfinder.model.Track;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

public interface TrackService {
    Track createTrack(Track newTrack) throws FileNotFoundException;

    List<TrackDto> getNearestTrack(double lat, double lon);

    Track getTrackById (UUID tackId);

    void delete(UUID trackId);
}


