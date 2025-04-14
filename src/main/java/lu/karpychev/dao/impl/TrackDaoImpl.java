package lu.karpychev.dao.impl;

import lombok.RequiredArgsConstructor;
import lu.karpychev.dao.TrackDao;
import lu.karpychev.model.Track;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TrackDaoImpl implements TrackDao {

    private final JdbcTemplate jdbcTemplate;


    @Override
    public Track add(Track newTrail) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("trails")
                .usingGeneratedKeyColumns("id");
        newTrail.setId(simpleJdbcInsert.executeAndReturnKey(trailToMap(newTrail)).longValue());
        return newTrail;
    }


    private Map<String, Object> trailToMap(Track trail) {
        Map<String, Object> values = new HashMap<>();
        values.put("title", trail.getType());
        values.put("description", trail.getDescription());
        //values.put("points", trail.getPoints());
        //values.put("type_id",trail.getType().getId());
        //values.put("duration", trail.getDuration());
        //values.put("complexity", trail.getComplexity()Ж

        return values;
    }
}
