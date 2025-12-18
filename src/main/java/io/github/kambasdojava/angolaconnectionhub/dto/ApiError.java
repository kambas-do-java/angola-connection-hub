package io.github.kambasdojava.angolaconnectionhub.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ApiError {
  private String code;
  private String message;
  private LocalDateTime timestamp;
  private List<ApiErrorDetail> details;

  public record ApiErrorDetail(String code, String field, String message) {

  }
}
