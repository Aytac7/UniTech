package com.example.unitech.exception;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException( String code,String message){
        super(message);
    }
}
