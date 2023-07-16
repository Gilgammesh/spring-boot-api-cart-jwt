package com.santander.apicartapp.exception;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadRequestValidException extends RuntimeException {
    private static final long serialVersionUID = -167199231L;

    private final List<String> errors;

    public BadRequestValidException(List<String> errors) {
        this.errors = errors;
    }
}
