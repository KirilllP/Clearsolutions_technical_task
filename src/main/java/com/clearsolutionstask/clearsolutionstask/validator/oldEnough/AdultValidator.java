package com.clearsolutionstask.clearsolutionstask.validator.oldEnough;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.Period;

public class AdultValidator implements ConstraintValidator<OldEnough, LocalDate> {

    @Value("${property.minimalAge}")
    int minimalAge;

    @Override
    public void initialize(OldEnough constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext context) {
        if (birthDate == null) {
            return false;
        }
        LocalDate now = LocalDate.now();
        Period period = Period.between(birthDate, now);
        return period.getYears() >= minimalAge;
    }
}

