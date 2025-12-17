package com.example.product_service.exception;

public class DuplicateProductCodeException extends RuntimeException {
    public DuplicateProductCodeException(String message) {
        super(message);
    }
}