package lu.karpychev.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PointOfTrailDbStorage {
        /*private final JdbcTemplate jdbcTemplate;

        private final static String ADD_POINT_OF_TRAIL = "insert " +
                "into _of_film " +
                "(genre_id, film_id) " +
                "values (?, ?)";
        private final static String DELETE_GENRE_OF_FILM = "delete " +
                "from genre_of_film " +
                "where film_id = ?";
        private final static String FIND_GENRE = "select " +
                "gf.genre_id, " +
                "g.genre_name, " +
                "from genres as g " +
                "inner join genre_of_film as gf on g.genre_id = gf.genre_id " +
                "where gf.film_id = ? " +
                "order by gf.genre_id asc";*/
}
