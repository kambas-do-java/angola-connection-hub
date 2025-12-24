package io.github.kambasdojava.angolaconnectionhub.handlers;

import io.github.kambasdojava.angolaconnectionhub.dto.ApiError;
import io.github.kambasdojava.angolaconnectionhub.dto.ApiError.ApiErrorDetail;
import io.github.kambasdojava.angolaconnectionhub.exceptions.ACHException;
import io.github.kambasdojava.angolaconnectionhub.filters.CorrelationIdFilter;
import io.github.kambasdojava.angolaconnectionhub.handlers.docs.GlobalExceptionHandlerDocs;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static io.github.kambasdojava.angolaconnectionhub.filters.CorrelationIdFilter.CORRELATION_ID;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler implements GlobalExceptionHandlerDocs {

  @ExceptionHandler(ACHException.class)
  @Override
  public ResponseEntity<@NonNull ApiError> handleResourceNotFoundException(ACHException ex) {
    return ResponseEntity.status(ex.getStatus())
        .body(ApiError.builder()
            .code(ex.getCode())
            .message(ex.getMessage())
            .correlationId(CORRELATION_ID.get())
            .timestamp(LocalDateTime.now())
            .build()
        );
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @Override
  public ResponseEntity<@NonNull ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    log.error("Erro de validação: ", ex);

    // Verifique se os erros existem
    if (ex.getBindingResult().hasErrors()) {
      var errorDetails = ex.getBindingResult().getFieldErrors().stream()
          .map(error -> new ApiErrorDetail(
              error.getCode(),
              error.getField(),
              error.getDefaultMessage()
          ))
          .toList();

      var apiError = ApiError.builder()
          .code("VALIDATION_ERROR")
          .message("Validation error in the provided fields")
          .correlationId(CORRELATION_ID.get())
          .timestamp(LocalDateTime.now())
          .details(errorDetails)
          .build();

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }


    // Caso não haja erros
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ApiError.builder().correlationId(CORRELATION_ID.get()).build());
  }
}
