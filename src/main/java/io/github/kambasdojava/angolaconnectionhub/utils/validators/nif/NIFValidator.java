package io.github.kambasdojava.angolaconnectionhub.utils.validators.nif;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

import static java.util.Objects.isNull;

public class NIFValidator implements ConstraintValidator<NIF, String> {
  private String regex;

  @Override
  public void initialize(NIF constraintAnnotation) {
    NIFType type = Optional.ofNullable(constraintAnnotation.type()).orElse(NIFType.ALL);

    regex = switch (type) {
      case ALL -> "^(\\d{4,12})|(\\d{9}(LA|BO|BA|ZE|UE|MO|ME|HO|KN|KS|LS|LN|CE|HA|BE|CA|NE)\\d{3})$";
      case PERSON -> "^\\d{9}(LA|BO|BA|ZE|UE|MO|ME|HO|KN|KS|LS|LN|CE|HA|BE|CA|NE)\\d{3}$";
      case COMPANY -> "^\\d{4,12}$";
    };
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    if (isNull(s)) {
      return false;
    }

    return s.trim().toUpperCase().matches(regex);
  }
}
