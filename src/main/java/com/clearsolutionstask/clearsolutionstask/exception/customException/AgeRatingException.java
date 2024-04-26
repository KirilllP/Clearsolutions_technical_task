package com.clearsolutionstask.clearsolutionstask.exception.customException;

import jakarta.validation.ConstraintDeclarationException;

public class AgeRatingException extends ConstraintDeclarationException {

    public AgeRatingException(String message) {
        super(message);
    }
}
