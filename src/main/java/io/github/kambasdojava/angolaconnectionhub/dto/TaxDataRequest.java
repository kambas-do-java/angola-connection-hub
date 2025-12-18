package io.github.kambasdojava.angolaconnectionhub.dto;

import io.github.kambasdojava.angolaconnectionhub.utils.validators.nif.NIF;
import jakarta.validation.constraints.NotBlank;
import org.jspecify.annotations.NonNull;

public record TaxDataRequest(@NotBlank @NonNull @NIF String taxId) {
}
