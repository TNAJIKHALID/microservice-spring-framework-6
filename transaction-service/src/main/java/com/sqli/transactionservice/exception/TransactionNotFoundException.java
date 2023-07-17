package com.sqli.transactionservice.exception;

public class TransactionNotFoundException extends RuntimeException{
    public TransactionNotFoundException(String message) {
        super(message);
    }
}
