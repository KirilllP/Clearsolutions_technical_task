package com.clearsolutionstask.clearsolutionstask.exception.customException;

import jakarta.validation.ConstraintDeclarationException;

public class FromToValidationException extends ConstraintDeclarationException {
    public FromToValidationException(String message) {
        super(message);
    }
}
