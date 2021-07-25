package com.panko.onetouchtestapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Error> handleServerWebInputException(MissingServletRequestParameterException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(ex.getMessage()));
    }

    @ExceptionHandler(CurrencyException.class)
    public ResponseEntity<Error> handleCurrencyException(CurrencyException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(ex.getMessage()));
    }
}
