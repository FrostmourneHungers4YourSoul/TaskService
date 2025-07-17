package com.example.taskservice.handler;

import com.example.taskservice.dto.ExceptionResponse;
import com.example.taskservice.exception.ResourceAlreadyExistsException;
import com.example.taskservice.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicateException(
        ResourceAlreadyExistsException exception) {

        ExceptionResponse response = new ExceptionResponse(
            HttpStatus.CONFLICT.value(),
            "Conflict",
            exception.getMessage()
        );
        return ResponseEntity.status(response.statusCode())
            .body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(
        ResourceNotFoundException exception) {

        ExceptionResponse response = new ExceptionResponse(
            HttpStatus.NOT_FOUND.value(),
            "Not found",
            exception.getMessage()
        );
        return ResponseEntity.status(response.statusCode())
            .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {

        ExceptionResponse response = new ExceptionResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal Server Error",
            exception.getMessage()
        );
        return ResponseEntity.status(response.statusCode())
            .body(response);
    }
}
