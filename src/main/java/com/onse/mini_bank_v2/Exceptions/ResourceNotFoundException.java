package com.onse.mini_bank_v2.Exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public  ResourceNotFoundException(String message) {
        super(message);
    }
}
