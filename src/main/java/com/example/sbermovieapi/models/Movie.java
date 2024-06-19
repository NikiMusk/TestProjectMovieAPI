package com.example.sbermovieapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="name")
    @NotBlank(message = "Имя фильма обязательно для заполнения")
    private String name;
    @Column(name="description")
    @Size(min=0, max=1000, message="Описание разрешено указывать в диапозоне от 0 до 1000 символов")
    private String description;
    @Column(name="genre")
    @Size(min=0, max=100, message="Жанр разрешено указывать в диапозоне от 0 до 100 символов")
    private String genre;
    @Column(name="movieType")
    @Enumerated(EnumType.STRING)
    private MovieType movieType;
    @Column(name="releaseDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
}
