package com.project.store.exceptions;

public class SaleNotFoundException extends RuntimeException{
    public SaleNotFoundException(String message) {
        super(message);
    }
}
