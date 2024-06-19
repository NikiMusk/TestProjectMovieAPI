package com.example.sbermovieapi.services;

import com.example.sbermovieapi.dto.MovieDTO;
import com.example.sbermovieapi.models.Movie;
import com.example.sbermovieapi.repository.MoviesRepository;
import com.example.sbermovieapi.exception.MovieNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MoviesService {
    private final MoviesRepository moviesRepository;
    private final ModelMapper modelMapper;

    public List<MovieDTO> getAllMovies(String name, Integer releaseYear, String movieType){
        return moviesRepository.getMoviesByRequestParameters(name, releaseYear, movieType).stream().map(this::convertToMovieDTO).toList();
    }

    public void addMovies(List<MovieDTO> moviesDTO) throws ConstraintViolationException {
        List<Movie> movies = moviesDTO.stream().map(this::convertToMovie).toList();
        moviesRepository.saveAll(movies);
    }

    public MovieDTO getMovie(Integer id) {
        Optional<Movie> optionalMovie = moviesRepository.findById(id);
        return convertToMovieDTO(optionalMovie.orElseThrow(MovieNotFoundException::new));
    }

    private Movie convertToMovie(MovieDTO movieDTO){
        return modelMapper.map(movieDTO, Movie.class);
    }

    private MovieDTO convertToMovieDTO(Movie movie){
        return modelMapper.map(movie, MovieDTO.class);
    }
}
