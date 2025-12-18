package io.github.kambasdojava.angolaconnectionhub.utils.validators.nif;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = NIFValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD, ElementType.FIELD })
public @interface NIF {
  NIFType type() default NIFType.ALL;
  String message() default "Invalid NIF";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
