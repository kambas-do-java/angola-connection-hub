package io.github.kambasdojava.angolaconnectionhub.exceptions;

public class ConnectionTimeoutException extends ACHException {
  public ConnectionTimeoutException(String message) {
    super(500, "CONNECTION_TIMEOUT", message);
  }
}
