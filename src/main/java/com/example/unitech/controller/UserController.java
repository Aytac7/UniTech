package com.example.unitech.controller;

import com.example.unitech.dto.request.create.UserCreateRequest;
import com.example.unitech.dto.request.login.ForgotPasswordRequest;
import com.example.unitech.dto.request.login.UserLoginRequest;
import com.example.unitech.dto.response.create.UserCreateResponse;
import com.example.unitech.service.user.UserLoginService;
import com.example.unitech.service.user.UserCreateService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserCreateService userCreateService;
    private final UserLoginService userLoginService;

    @PostMapping("/create")
    public UserCreateResponse createUser(@RequestBody UserCreateRequest userRequest) {
       return userCreateService.createUser(userRequest);

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser( @RequestBody UserLoginRequest userLogin) {
        return ResponseEntity.status(HttpStatus.OK).body(userLoginService.login(userLogin));
    }


    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) throws MessagingException {
        return ResponseEntity.status(HttpStatus.OK).body(userLoginService.forgotPassword(forgotPasswordRequest));
    }
}