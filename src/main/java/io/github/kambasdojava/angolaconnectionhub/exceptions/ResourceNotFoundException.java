package io.github.kambasdojava.angolaconnectionhub.exceptions;

public class ResourceNotFoundException extends ACHException {
  public ResourceNotFoundException(String message) {
    super(404, "NOT_FOUND", message);
  }
}
