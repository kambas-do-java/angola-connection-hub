package io.github.kambasdojava.angolaconnectionhub.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ValidateTaxIdResponse(
    @NonNull String taxId,
    @NonNull String name,
    @Nullable String province,
    @NonNull String type,
    @NonNull Boolean isActive) {
}
