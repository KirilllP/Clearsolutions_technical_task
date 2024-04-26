package com.clearsolutionstask.clearsolutionstask.validator.oldEnough;


import com.clearsolutionstask.clearsolutionstask.exception.customException.AgeRatingException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.Period;

public class AgeValidator implements ConstraintValidator<OldEnough, LocalDate> {

    @Value("${property.minimalAge}")
    int minimalAge;

    @Override
    public void initialize(OldEnough constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext context) {
        LocalDate now = LocalDate.now();
        if (birthDate == null || Period.between(birthDate, now).getYears() >= minimalAge) return true;
        throw new AgeRatingException("You must be older than " + minimalAge);
    }
}

