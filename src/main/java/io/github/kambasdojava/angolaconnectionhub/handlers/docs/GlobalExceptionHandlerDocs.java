package io.github.kambasdojava.angolaconnectionhub.handlers.docs;

import io.github.kambasdojava.angolaconnectionhub.dto.ApiError;
import io.github.kambasdojava.angolaconnectionhub.exceptions.ACHException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface GlobalExceptionHandlerDocs {

  @ApiResponse(
      responseCode = "500",
      description = "Internal Server Error",
      content = @Content(
          schema = @Schema(implementation = ApiError.class),
          mediaType = APPLICATION_JSON_VALUE
      )
  )
  @ApiResponse(
      responseCode = "400",
      description = "Bad Request",
      content = @Content(
          schema = @Schema(implementation = ApiError.class),
          mediaType = APPLICATION_JSON_VALUE
      )
  )
  @ApiResponse(
      responseCode = "404",
      description = "Not Found",
      content = @Content(
          schema = @Schema(implementation = ApiError.class),
          mediaType = APPLICATION_JSON_VALUE
      )
  )
  @ApiResponse(
      responseCode = "504",
      description = "Gateway Timeout",
      content = @Content(
          schema = @Schema(implementation = ApiError.class),
          mediaType = APPLICATION_JSON_VALUE
      )
  )
  ResponseEntity<@NonNull ApiError> handleResourceNotFoundException(ACHException ex);

  @ApiResponse(
      responseCode = "400",
      description = "Bad Request",
      content = @Content(
          schema = @Schema(implementation = ApiError.class),
          mediaType = APPLICATION_JSON_VALUE
      )
  )
  ResponseEntity<@NonNull ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex);
}
