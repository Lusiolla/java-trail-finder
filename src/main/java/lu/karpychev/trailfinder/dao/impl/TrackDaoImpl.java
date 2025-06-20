package lu.karpychev.trailfinder.dao.impl;

import lombok.RequiredArgsConstructor;
import lu.karpychev.trailfinder.dao.TrackDao;
import lu.karpychev.trailfinder.dto.TrackDto;
import lu.karpychev.trailfinder.model.Track;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.*;

import static lu.karpychev.trailfinder.mapper.TrackMapper.*;

@Component
@RequiredArgsConstructor
public class TrackDaoImpl implements TrackDao {

    private final JdbcTemplate jdbcTemplate;

    //private final int SRID = 4326;

    private final static String FIND_BY_ID_TRACK = "select * " +
            "from tracks " +
            "where id = ?";

    private final static String FIND_NEAREST_TRACK = "select " +
            "t.id, " +
            "t.name, " +
            "ST_Distance(ST_SetSRID(ST_MakePoint(?, ?), 4326)::geography, t.points::geography) AS distance_meters " +
            "from tracks t " +
            "order by " +
            "distance_meters " +
            "LIMIT 3";


    @Override
    public void add(Track newTrack) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("tracks");
        simpleJdbcInsert.execute(trackToMap(newTrack));
    }

    @Override
    public TrackDto findNearestTrack(double lat, double lon) throws FileNotFoundException {
        Optional<TrackDto> track = jdbcTemplate.
                query(FIND_NEAREST_TRACK,
                        (rs, rowNum) -> makeTrackDto(rs), lon, lat)
                .stream()
                .findFirst();

        if (track.isPresent()) {
            return track.get();
        } else {
            throw new FileNotFoundException();
        }
    }

    @Override
    public Track findById(UUID id) throws FileNotFoundException {
        Optional<Track> track = jdbcTemplate
                .query(FIND_BY_ID_TRACK,
                        (rs, rowNum) -> makeTrack(rs), id)
                .stream()
                .findFirst();

        if (track.isPresent()) {
            return track.get();
        } else {
            throw new FileNotFoundException();
        }
    }

}
