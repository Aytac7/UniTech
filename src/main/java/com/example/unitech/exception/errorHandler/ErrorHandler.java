package com.example.unitech.exception.errorHandler;

import com.example.unitech.exception.*;
import com.example.unitech.exception.IllegalArgumentException;
import com.example.unitech.exception.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerAccountNotFoundException(Exception exception){
        log.error("handlerAccountNotFoundException {}", exception.getMessage());

        return ErrorResponse.builder()
                .code(HttpStatus.NOT_FOUND.name())
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(UserExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse  handlerUserExitsException(Exception exception){
        log.error("handlerUserExitsException {}", exception.getMessage());

        return ErrorResponse.builder()
                .code(HttpStatus.CONFLICT.name())
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerUserNotFoundException(Exception exception){
        log.error("handlerUserNotFoundException {}", exception.getMessage());

        return ErrorResponse.builder()
                .code(HttpStatus.NOT_FOUND.name())
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(CustomEmailException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerCustomEmailException(Exception exception){
        log.error("handlerCustomEmailException {}", exception.getMessage());

        return ErrorResponse.builder()
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(SameAccountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerSameAccountException(Exception exception){
        log.error("handlerSameAccountException {}", exception.getMessage());

        return ErrorResponse.builder()
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(AccountActivationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerAccountActivationException(Exception exception){
        log.error("handlerAccountActivationException {}", exception.getMessage());

        return ErrorResponse.builder()
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerInsufficientBalanceException(Exception exception){
        log.error("handlerAccountActivationException {}", exception.getMessage());

        return ErrorResponse.builder()
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerIllegalArgumentException(Exception exception){
        log.error("handlerIllegalArgumentException {}", exception.getMessage());

        return ErrorResponse.builder()
                .message(exception.getMessage())
                .code(HttpStatus.BAD_REQUEST.name())
                .build();
    }
}
