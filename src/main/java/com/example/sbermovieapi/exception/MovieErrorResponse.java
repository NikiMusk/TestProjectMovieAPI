package com.example.sbermovieapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class MovieErrorResponse {
    private String message;
    private Timestamp datetime;
}
