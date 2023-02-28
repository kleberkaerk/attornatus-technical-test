package com.attornatustechnicaltest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NonExistentPersonException extends RuntimeException{

    public NonExistentPersonException(String message) {

        super(message);
    }
}
