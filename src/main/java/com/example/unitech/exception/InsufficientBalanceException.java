package com.example.unitech.exception;

public class InsufficientBalanceException extends RuntimeException{

    public InsufficientBalanceException(String code,String message){
        super(message);
    }
}
