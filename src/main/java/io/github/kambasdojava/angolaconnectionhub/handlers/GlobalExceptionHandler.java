package io.github.kambasdojava.angolaconnectionhub.handlers;

import io.github.kambasdojava.angolaconnectionhub.dto.ApiError;
import io.github.kambasdojava.angolaconnectionhub.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ApiError.builder()
            .code("NOT_FOUND")
            .message("Resource not found")
            .timestamp(LocalDateTime.now())
            .details(List.of(new ApiError.ApiErrorDetail(
                null,
                ex.getMessage()
            ))));
  }
}
