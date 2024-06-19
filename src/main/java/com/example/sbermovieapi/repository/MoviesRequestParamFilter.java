package com.example.sbermovieapi.repository;

import com.example.sbermovieapi.models.Movie;

import java.util.Date;
import java.util.List;
public interface MoviesRequestParamFilter {
    List<Movie> getMoviesByRequestParameters(String name, Integer releaseYear, String movieType);
}
