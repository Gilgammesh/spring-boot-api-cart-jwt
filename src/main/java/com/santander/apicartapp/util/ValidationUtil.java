package com.santander.apicartapp.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;

import com.santander.apicartapp.exception.BadRequestValidException;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static void handleValidationErrors(BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "Field: " + err.getField() + ", " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            throw new BadRequestValidException(errors);
        }
    }
}
