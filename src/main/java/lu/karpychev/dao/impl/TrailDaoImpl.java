package lu.karpychev.dao.impl;

import lombok.RequiredArgsConstructor;
import lu.karpychev.dao.TrailDao;
import lu.karpychev.model.Trail;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TrailDaoImpl implements TrailDao {

    private final JdbcTemplate jdbcTemplate;



    @Override
    public Trail add(Trail trail) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("trails")
                .usingGeneratedKeyColumns("id");
        trail.setId(simpleJdbcInsert.executeAndReturnKey(trailToMap(trail)).longValue());
        return trail;
    }


    private Map<String, Object> trailToMap(Trail trail) {
        Map<String, Object> values = new HashMap<>();
        values.put("title", trail.getTitle());
        values.put("description", trail.getDescription());
        values.put("points", trail.getPoints());
        //values.put("type_id",trail.getType().getId());
        //values.put("duration", trail.getDuration());
        //values.put("complexity", trail.getComplexity());


        return values;
    }
}
