package com.example.unitech.exception;

public class UserExistsException extends RuntimeException {
    public UserExistsException(String code, String message){
        super(message);
    }
}
