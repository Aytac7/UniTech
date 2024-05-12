package com.example.unitech.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message,String code){
        super(message);
    }

}
