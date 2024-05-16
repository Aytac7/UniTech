package com.example.unitech.exception;

public class AccountActivationException extends RuntimeException {
    public AccountActivationException(String code,String message){
        super(message);
    }
}
