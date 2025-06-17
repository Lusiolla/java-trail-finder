package lu.karpychev.trailfinder.dao;

import lu.karpychev.trailfinder.dto.TrackDto;
import lu.karpychev.trailfinder.model.Track;

import java.io.FileNotFoundException;

public interface TrackDao {

    Track add(Track newTrail) throws FileNotFoundException;

    TrackDto findNearestTrack (double lat, double lon) throws FileNotFoundException;

}
