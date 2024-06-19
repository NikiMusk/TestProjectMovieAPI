package com.example.sbermovieapi.dto;

import com.example.sbermovieapi.models.MovieType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
public class MovieDTO {
    @NotBlank(message = "Имя фильма обязательно для заполнения")
    private String name;
    @Size(min=0, max=1000, message="Описание разрешено указывать в диапозоне от 0 до 1000 символов")
    private String description;
    @Size(min=0, max=100, message="Жанр разрешено указывать в диапозоне от 0 до 100 символов")
    private String genre;
    private MovieType movieType;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
}
