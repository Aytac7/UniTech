package com.example.unitech.exception;

public class AccountActivationException extends RuntimeException {
    public AccountActivationException(String message, String code){
        super(message);
    }
}
