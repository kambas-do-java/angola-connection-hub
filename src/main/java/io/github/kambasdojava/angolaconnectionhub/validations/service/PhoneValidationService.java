package io.github.kambasdojava.angolaconnectionhub.validations.service;

import io.github.kambasdojava.angolaconnectionhub.validations.dto.PhoneValidationErrorResponse;
import io.github.kambasdojava.angolaconnectionhub.validations.dto.PhoneValidationSuccessResponse;
import io.github.kambasdojava.angolaconnectionhub.validations.dto.Detail;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class PhoneValidationService
{
    private static final String regex = "([+]?244)?()?\\d{9}";

    public Object validatePhone(String number, List<String> types)
    {
        List<Detail>    details;

        details = new ArrayList<>();
        if (types.contains("format") && isInvalidFormat(number, details))
            return (buildErrorResponse(details));
        if (types.contains("exists") && isNumberUnexistent(number, details))
            return (buildErrorResponse(details));
        details.add(new Detail("number", "VALID_FORMAT", "O formato do telefone é valido"));
        return (new PhoneValidationSuccessResponse(true, "Os dados fornecidos são válidos", details));
    }

    private boolean isInvalidFormat(String number, List<Detail> details)
    {
        if (!(number.matches(regex))) {
            details.add(new Detail("number", "INVALID FORMAT", "O formato do telefone é inválido"));
            return (true);
        }
        return (false);
    }

    private boolean isNumberUnexistent(String number, List<Detail> details)
    {
        String          cleanNumber; 
        String          numberPrefix;

        if (number.length() < 9) {
            details.add(new Detail("number", "INVALID FORMAT", "O formato do telefone é inválido"));
            return (true);
        }
        cleanNumber = number.substring(number.length() - 9);
        numberPrefix = cleanNumber.substring(0, 2);
        if (!(List.of("91", "92", "93", "94", "95", "97", "99").contains(numberPrefix))) {
            details.add(new Detail("number", "NUMBER NOT FOUND", "O número fornecido não existe em nenhuma operadora"));
            return (true);
        }
        return (false);
    }

    private PhoneValidationErrorResponse buildErrorResponse(List<Detail> details) {
        return (new PhoneValidationErrorResponse(
            new PhoneValidationErrorResponse.ErrorDetails("VALIDATION_ERROR", "Os dados fornecidos são inválidos", details)));
    }
}
