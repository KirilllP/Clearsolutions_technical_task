package com.clearsolutionstask.clearsolutionstask.exception.customException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class WrongIdException extends RuntimeException {

    public WrongIdException(String message) {
        super(message);
    }

}