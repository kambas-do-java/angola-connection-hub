package io.github.kambasdojava.angolaconnectionhub.exceptions;

import org.springframework.http.HttpStatus;

public class RequestTimeoutException extends ACHException {
  public RequestTimeoutException(String message) {
    super(HttpStatus.GATEWAY_TIMEOUT.value(), "GATEWAY_TIMEOUT", message);
  }
}
