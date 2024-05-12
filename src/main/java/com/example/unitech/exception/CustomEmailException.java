package com.example.unitech.exception;

public class CustomEmailException extends RuntimeException{

    public CustomEmailException(String code,String message){
        super(message);
    }
}
