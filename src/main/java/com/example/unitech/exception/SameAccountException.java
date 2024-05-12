package com.example.unitech.exception;

public class SameAccountException extends RuntimeException{
    public SameAccountException(String code,String message){
        super(message);
    }
}
