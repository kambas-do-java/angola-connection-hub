package io.github.kambasdojava.angolaconnectionhub.validations.dto;

import java.util.List;

public record PhoneValidationErrorResponse(
    ErrorDetails error
) {
    public record ErrorDetails(
        String code,
        String message,
        List<Detail> details
    ) {}
}
