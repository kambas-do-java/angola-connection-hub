package io.github.kambasdojava.angolaconnectionhub.dto;

import org.jspecify.annotations.NonNull;

public record ValidateTaxIdResponse(
    @NonNull String name,
    @NonNull String taxId,
    @NonNull String type,
    @NonNull Boolean isActive) {
}
