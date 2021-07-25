package com.panko.onetouchtestapp.services;

import com.panko.onetouchtestapp.validation.ValidationError;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GameValidationService {

    public Collection<ValidationError> validateGameApiRequest(Integer operatorId, Integer gameId, String currency) {
        List<ValidationError> errors = new ArrayList<>();

        if (!validateCurrency(currency)) {
            errors.add(new ValidationError("Currency is not correct"));
        }

        return errors;
    }

    private boolean validateCurrency(String currency) {
        try {
            java.util.Currency.getInstance(currency);
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }
}
