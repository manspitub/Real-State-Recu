package com.salesianos.triana.RealState.Recu.RealStateRecu.validators;

import com.salesianos.triana.RealState.Recu.RealStateRecu.anotations.ValidLating;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidLatingValidator implements ConstraintValidator<ValidLating, String> {
    @Override
    public void initialize(ValidLating constraintAnnotation) { }

    @Override
    public boolean isValid(String location, ConstraintValidatorContext context) {
        String lonMatch = "[\\-+]?(0?\\d{1,2}|0?\\d{1,2}\\.\\d{1,15}|1[0-7]?\\d|1[0-7]?\\d\\.\\d{1,15}|180|180\\.0{1,15}),[\\-+]?([0-8]?\\d|[0-8]?\\d\\.\\d{1,15}|90|90\\.0{1,15})";

        if(location.matches(lonMatch))
            return true;
        else {
            return false;
        }
    }
}
