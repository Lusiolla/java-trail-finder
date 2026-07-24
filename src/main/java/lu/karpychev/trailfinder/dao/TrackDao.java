package lu.karpychev.trailfinder.dao;

import lu.karpychev.trailfinder.dto.TrackDto;
import lu.karpychev.trailfinder.model.Track;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TrackDao {

    void add(Track newTrail);

    Optional<Track> findById(UUID id);

    List<TrackDto> findNearestTrack (double lat, double lon);

    void delete(UUID id);

}
