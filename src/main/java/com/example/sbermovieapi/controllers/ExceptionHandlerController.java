package com.example.sbermovieapi.controllers;

import com.example.sbermovieapi.exception.MovieErrorResponse;
import com.example.sbermovieapi.exception.MovieNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Set;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerController {
    public static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler
    private ResponseEntity<MovieErrorResponse> handleException(MovieNotFoundException e) {
        logger.error("Ошибка, фильм не найден " + e.toString());
        MovieErrorResponse movieErrorResponse = new MovieErrorResponse(
                "Фильм не найден",
                new Timestamp(System.currentTimeMillis())
        );
        return new ResponseEntity<>(movieErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<MovieErrorResponse> handleValidationExceptions(ConstraintViolationException e){
        logger.error("Фильм не добавлен " + e.toString());

        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        List<String> messages = constraintViolations.stream().map(ConstraintViolation::getMessage).toList();

        MovieErrorResponse movieErrorResponse = new MovieErrorResponse(
                "Фильм не добавлен. " + messages,
                new Timestamp(System.currentTimeMillis())
        );
        return new ResponseEntity<>(movieErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<MovieErrorResponse> handleValidationDateTimeExceptions(DateTimeParseException e){
        logger.error("Фильм не добавлен, дата указана неверно" + e.toString());

        MovieErrorResponse movieErrorResponse = new MovieErrorResponse(
                "Фильм не добавлен, ошибка с датой. " + e.getMessage(),
                new Timestamp(System.currentTimeMillis())
        );
        return new ResponseEntity<>(movieErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
