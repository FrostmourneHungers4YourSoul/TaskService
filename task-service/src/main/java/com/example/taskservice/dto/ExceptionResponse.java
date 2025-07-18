package com.example.taskservice.dto;

public record ExceptionResponse
    (
        int statusCode,
        String error,
        String message
    ) {

}
