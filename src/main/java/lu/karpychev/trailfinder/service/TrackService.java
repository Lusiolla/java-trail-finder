package lu.karpychev.trailfinder.service;

import lombok.Data;
import lu.karpychev.trailfinder.dao.TrackDao;
import lu.karpychev.trailfinder.model.GpxFile;
import lu.karpychev.trailfinder.model.Track;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
@Component
@Data
public class TrackService {

    private final TrackDao trackDao;

    public Track createTrackFromFile(GpxFile file) throws FileNotFoundException {
        return trackDao.add(file.getTrack());
    }
}
