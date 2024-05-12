package com.example.unitech.exception;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String message, String code){
        super(message);
    }
}
