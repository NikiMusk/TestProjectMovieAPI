package com.example.sbermovieapi.repository;

import com.example.sbermovieapi.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Integer>, MoviesRequestParamFilter {

}
