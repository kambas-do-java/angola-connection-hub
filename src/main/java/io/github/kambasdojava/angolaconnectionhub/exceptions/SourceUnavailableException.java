package io.github.kambasdojava.angolaconnectionhub.exceptions;

public class SourceUnavailableException extends RuntimeException {
  public SourceUnavailableException(String source) {
    super("Source %s unavailable.".formatted(source));
  }
}
