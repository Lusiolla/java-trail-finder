package lu.karpychev.trailfinder.service.impl;

import lombok.Data;
import lu.karpychev.trailfinder.dao.TrackDao;
import lu.karpychev.trailfinder.dto.TrackDto;
import lu.karpychev.trailfinder.exception.ObjectNotFoundException;
import lu.karpychev.trailfinder.model.Track;
import lu.karpychev.trailfinder.service.TrackService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@Component
@Data
public class TrackServiceImpl implements TrackService {

    private final TrackDao trackDao;

    @Override
    public Track createTrack(Track newTrack) {
        newTrack.setId(UUID.randomUUID());
        trackDao.add(newTrack);
        return newTrack;
    }

    @Override
    public List<TrackDto> getNearestTrack(double lat, double lon) throws ObjectNotFoundException {
        List<TrackDto> nearestTracks = trackDao.findNearestTrack(lat, lon);
        if (nearestTracks != null && !nearestTracks.isEmpty()) {
            return nearestTracks;
        } else {
            throw new ObjectNotFoundException("Nearest tracks was not found");
        }
    }

    @Override
    public Track getTrackById(UUID trackId) {
        return trackDao.findById(trackId).orElseThrow(() -> new ObjectNotFoundException("Track", trackId));
    }

    @Override
    public void delete(UUID trackId) {
        trackDao.delete(trackId);
    }

}
