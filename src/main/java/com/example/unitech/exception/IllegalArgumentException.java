package com.example.unitech.exception;

public class IllegalArgumentException extends RuntimeException {

    public IllegalArgumentException(String code,String message){
        super(message);
    }
}
