package lu.karpychev.trailfinder.dao;

import lu.karpychev.trailfinder.dto.TrackDto;
import lu.karpychev.trailfinder.model.Track;

import java.io.FileNotFoundException;
import java.util.UUID;

public interface TrackDao {

    void add(Track newTrail);

    Track findById(UUID id) throws FileNotFoundException;

    TrackDto findNearestTrack (double lat, double lon) throws FileNotFoundException;

}
