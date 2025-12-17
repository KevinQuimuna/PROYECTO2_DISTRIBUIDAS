package com.example.supplier_service.exception;

public class DuplicateSupplierException extends RuntimeException {
    public DuplicateSupplierException(String message) {
        super(message);
    }
}