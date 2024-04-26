package com.clearsolutionstask.clearsolutionstask.validator.fromToDates;

import com.clearsolutionstask.clearsolutionstask.dto.DateRangeDto;
import com.clearsolutionstask.clearsolutionstask.exception.customException.FromToValidationException;
import com.clearsolutionstask.clearsolutionstask.validator.oldEnough.OldEnough;
import jakarta.validation.ConstraintDeclarationException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDate;
import java.time.Period;

public class FromToValidator implements ConstraintValidator<FromToDates, DateRangeDto> {

    private String exceptionMessage;


    @Override
    public void initialize(FromToDates constraintAnnotation) {
        exceptionMessage=constraintAnnotation.message();
    }

    @Override
    public boolean isValid(DateRangeDto dateRangeDto, ConstraintValidatorContext context) {
        if (dateRangeDto.getFrom().isBefore(dateRangeDto.getTo())) {
            return true;
        }
       throw new FromToValidationException(exceptionMessage);
    }
}
