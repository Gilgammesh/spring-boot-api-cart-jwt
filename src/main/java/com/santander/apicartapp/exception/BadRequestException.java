package com.santander.apicartapp.exception;

import java.util.List;

public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = -167199231L;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(List<String> messages) {
        super(String.join(", ", messages));
    }
}
