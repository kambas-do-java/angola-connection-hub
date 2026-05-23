package io.github.kambasdojava.angolaconnectionhub.validations.dto;

import java.util.List;

public record PhoneValidationSuccessResponse(boolean success, String message, List<Detail> details)
{
}
