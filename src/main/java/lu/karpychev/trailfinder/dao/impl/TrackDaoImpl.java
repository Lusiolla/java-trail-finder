package lu.karpychev.trailfinder.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lu.karpychev.trailfinder.dao.TrackDao;
import lu.karpychev.trailfinder.dto.TrackDto;
import lu.karpychev.trailfinder.model.Track;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.*;

import static lu.karpychev.trailfinder.mapper.TrackMapper.*;

@Component
@RequiredArgsConstructor
@Slf4j
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
    private final static String DELETE_TRACK = "delete " +
            "from tracks " +
            "where id = ?";


    @Override
    public void add(Track newTrack) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("tracks");
        simpleJdbcInsert.execute(trackToMap(newTrack));
    }

    @Override
    public List<TrackDto> findNearestTrack(double lat, double lon)  {
        return jdbcTemplate.
                query(FIND_NEAREST_TRACK,
                        (rs, rowNum) -> makeTrackDto(rs), lon, lat);

    }

    @Override
    public Optional<Track> findById(UUID id) {
        return jdbcTemplate
                .query(FIND_BY_ID_TRACK,
                        (rs, rowNum) -> makeTrack(rs), id)
                .stream()
                .findFirst();
    }

    @Override
    public void delete(UUID id) {
        if (jdbcTemplate.update(DELETE_TRACK, id) < 1) {
            log.info("Что-то пошло не так, трек {} не был найден", id);
        }
    }

}
