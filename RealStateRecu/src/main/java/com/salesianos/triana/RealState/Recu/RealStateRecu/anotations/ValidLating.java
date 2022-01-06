package com.salesianos.triana.RealState.Recu.RealStateRecu.anotations;


import com.salesianos.triana.RealState.Recu.RealStateRecu.validators.ValidLatingValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidLatingValidator.class)
@Documented


public @interface ValidLating {


    String message() default "Formato de ubicación invalido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
