package com.panko.onetouchtestapp.validation;

import lombok.Getter;

@Getter
public class ValidationError {

    private final String message;

    public ValidationError(String message) {
        this.message = message;
    }
}
