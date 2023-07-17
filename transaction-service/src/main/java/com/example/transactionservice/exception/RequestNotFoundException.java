package com.example.transactionservice.exception;

public class RequestNotFoundException extends RuntimeException{
    public RequestNotFoundException(String message) {
        super(message);
    }
}
