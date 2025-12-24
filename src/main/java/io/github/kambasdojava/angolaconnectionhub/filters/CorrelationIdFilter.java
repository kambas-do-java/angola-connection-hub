package io.github.kambasdojava.angolaconnectionhub.filters;

import io.github.kambasdojava.angolaconnectionhub.exceptions.ACHException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class CorrelationIdFilter extends OncePerRequestFilter {
  public static final ScopedValue<String> CORRELATION_ID = ScopedValue.newInstance();
  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain) {
    var correlationId = Optional.ofNullable(request.getHeader("ACH-Request-ID")).orElse(randomUUID().toString());

    ScopedValue.where(CORRELATION_ID, correlationId).run(() -> {
      try {
        response.addHeader("ACH-Request-ID", correlationId);
        filterChain.doFilter(request, response);
      } catch (Exception e) {
        if (e instanceof ACHException achEx) {
          throw achEx;
        }

        throw new ACHException(INTERNAL_SERVER_ERROR.value(), "INTERNAL_ERROR", e.getMessage());
      }
    });
  }
}
