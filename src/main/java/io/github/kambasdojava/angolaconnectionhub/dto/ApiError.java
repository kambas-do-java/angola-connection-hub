package io.github.kambasdojava.angolaconnectionhub.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public class ApiError {
  private String code;
  private String message;
  private LocalDateTime timestamp;
  private List<ApiErrorDetail> details;

  public record ApiErrorDetail(String code, String message) {

  }
}
