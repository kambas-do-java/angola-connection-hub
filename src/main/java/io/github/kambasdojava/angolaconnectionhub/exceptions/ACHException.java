package io.github.kambasdojava.angolaconnectionhub.exceptions;

import lombok.Getter;

@Getter
public class ACHException extends RuntimeException {
  private  final int status;
  private final String code;

  public ACHException(int status, String code, String message) {
    super(message);
    this.status = status;
    this.code = code;
  }
}
