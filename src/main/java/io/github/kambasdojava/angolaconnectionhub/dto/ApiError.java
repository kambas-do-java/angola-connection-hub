package io.github.kambasdojava.angolaconnectionhub.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ApiError(String code, String message, String correlationId, LocalDateTime timestamp,
                       List<ApiErrorDetail> details) {
  public record ApiErrorDetail(String code, String field, String message) {
  }
}
