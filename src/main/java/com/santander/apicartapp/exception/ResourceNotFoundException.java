package com.santander.apicartapp.exception;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -936084099L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
