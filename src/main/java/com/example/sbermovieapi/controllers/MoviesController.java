package com.example.sbermovieapi.controllers;

import com.example.sbermovieapi.dto.MovieDTO;
import com.example.sbermovieapi.services.MoviesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/movie-api/")
@Slf4j
@RequiredArgsConstructor
public class MoviesController {
    private final MoviesService moviesService;
    public static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @Operation(summary = "Получить фильм(ы) по таким параметрам запроса как: имя, год выхода, тип (полнометражный или короткометражный) ")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Фильм(ы) найдены",
                    content = { @Content(schema = @Schema(implementation = MovieDTO.class))
            })
    })
    @GetMapping(produces={APPLICATION_JSON_VALUE})
    public List<MovieDTO> movies(@RequestParam(name="name", required = false) String name,
                              @RequestParam(name="releaseYear", required = false) Integer releaseYear,
                              @RequestParam(name="movieType", required = false) String movieType){
        logger.info("Поиск фильмов по параметрам: наименование=" + name + ", год выпуска=" + releaseYear + ", тип фильма=" + movieType);
        return moviesService.getAllMovies(name, releaseYear, movieType);
    }

    @Operation(summary = "Загрузить фильм(ы)")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Фильм успешно добавлен"),
            @ApiResponse(responseCode = "400", description = "Фильм не загружен",
                    content = @Content),
    })
    @PostMapping(consumes={APPLICATION_JSON_VALUE}, produces={APPLICATION_JSON_VALUE})
    public void addMovies(@RequestBody List<MovieDTO> moviesDTO){
        logger.info("Добавление фильма(ов)");
        moviesService.addMovies(moviesDTO);
    }

    @Operation(summary = "Получить фильм по ID")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Фильм найден",
                    content = { @Content(schema = @Schema(implementation = MovieDTO.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Фильм не найден",
                    content = @Content),
    })
    @GetMapping(path="/{id}/", produces={APPLICATION_JSON_VALUE})
    public MovieDTO movie(@PathVariable(name="id") Integer id){
        logger.info("Поиск фильма по ID" + id);
        return moviesService.getMovie(id);
    }
}
