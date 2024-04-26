package com.clearsolutionstask.clearsolutionstask.validator.fromToDates;

import com.clearsolutionstask.clearsolutionstask.validator.oldEnough.AgeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FromToValidator.class)
@Documented
public @interface FromToDates {

    String message() default "From must be less than To";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
