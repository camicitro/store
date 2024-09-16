package com.project.store.exceptions;

public class SellerNotFoundException extends RuntimeException{
    public SellerNotFoundException(String message) {
        super(message);
    }
}
