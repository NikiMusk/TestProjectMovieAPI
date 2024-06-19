package com.example.sbermovieapi.repository;

import com.example.sbermovieapi.models.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MoviesRequestParamFilterImpl implements MoviesRequestParamFilter {

    private EntityManager em;

    public MoviesRequestParamFilterImpl() {
    }

    @Autowired
    public MoviesRequestParamFilterImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Movie> getMoviesByRequestParameters(String name, Integer releaseYear, String movieType) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Movie> cr = cb.createQuery(Movie.class);
        Root<Movie> root = cr.from(Movie.class);

        List<Predicate> predicates = new ArrayList<>();

        if ( !(name == null) ) {
            predicates.add(cb.equal(root.get("name"), name));
        }

        if ( !(movieType == null) ) {
            predicates.add(cb.equal(root.get("movieType"), movieType));
        }

        if ( !(releaseYear == null) ) {
            LocalDateTime localDateTimeYearStart = LocalDateTime.of(releaseYear, 1, 1, 0,0);
            LocalDateTime localDateTimeYearEnd = LocalDateTime.of(releaseYear, 12, 31, 23,59);

            Timestamp timestampYearStart = Timestamp.valueOf(localDateTimeYearStart);
            Timestamp timestampYearEnd = Timestamp.valueOf(localDateTimeYearEnd);

            predicates.add(cb.between(root.get("releaseDate"), timestampYearStart, timestampYearEnd));
        }

        cr.select(root).where(predicates.toArray(new Predicate[predicates.size()]));

        TypedQuery<Movie> query = em.createQuery(cr);
        List<Movie> movies = query.getResultList();

        return movies;
    }
}
