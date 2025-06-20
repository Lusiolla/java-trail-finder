package lu.karpychev.trailfinder.service;

import lombok.Data;
import lu.karpychev.trailfinder.dao.TrackDao;
import lu.karpychev.trailfinder.dto.TrackDto;
import lu.karpychev.trailfinder.model.GpxFile;
import lu.karpychev.trailfinder.model.Track;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.UUID;

@Service
@Component
@Data
public class TrackServiceImpl implements TrackService {

    private final TrackDao trackDao;

    @Override
    public UUID createTrackFromFile(GpxFile file) throws FileNotFoundException {
        Track newTrack = file.getTrack();
        newTrack.setId(UUID.randomUUID());
        trackDao.add(newTrack);
        return newTrack.getId();
    }

    @Override
    public TrackDto getNearestTrack(double lat, double lon) throws FileNotFoundException {
        return trackDao.findNearestTrack(lat, lon);
    }

    @Override
    public Track getTrackById(UUID trackId) throws FileNotFoundException {
        return trackDao.findById(trackId);
    }

    @Override
    public GpxFile getGpxFileById(UUID trackId) {
        return null;
    }

}
