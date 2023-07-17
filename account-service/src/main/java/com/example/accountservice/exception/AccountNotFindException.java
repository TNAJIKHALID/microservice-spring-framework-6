package com.example.accountservice.exception;

public class AccountNotFindException extends RuntimeException{
    public AccountNotFindException(String message) {
        super(message);
    }
}
