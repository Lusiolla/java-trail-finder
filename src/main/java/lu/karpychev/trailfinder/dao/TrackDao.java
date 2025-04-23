package lu.karpychev.trailfinder.dao;

import lu.karpychev.trailfinder.model.Track;

import java.io.FileNotFoundException;

public interface TrackDao {

    Track add(Track newTrail) throws FileNotFoundException;

}
