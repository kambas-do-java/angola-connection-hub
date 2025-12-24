package io.github.kambasdojava.angolaconnectionhub.dto;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public record TaxData(@NonNull String taxId,
                      @NonNull String name,
                      @Nullable String province,
                      @NonNull Type type,
                      @NonNull Boolean isActive) {
  public enum Type {P, U, E}
}
