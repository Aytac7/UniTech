package com.example.unitech.exception;

public class IllegalArgumentException extends RuntimeException {

    public IllegalArgumentException(String message,String code){
        super(message);
    }
}
