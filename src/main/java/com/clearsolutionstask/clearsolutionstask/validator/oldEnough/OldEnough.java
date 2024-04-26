package com.clearsolutionstask.clearsolutionstask.validator.oldEnough;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
@Documented
public @interface OldEnough {

    String message() default "you must be older";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}