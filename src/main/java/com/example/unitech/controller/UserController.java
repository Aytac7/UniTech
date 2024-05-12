package com.example.unitech.controller;

import com.example.unitech.dto.request.create.UserCreateRequest;
import com.example.unitech.dto.response.create.UserCreateResponse;
import com.example.unitech.service.create.UserCreateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserCreateService userCreateService;

    @PostMapping("/users")
    public UserCreateResponse createUser(@RequestBody UserCreateRequest userRequest) {
       return userCreateService.createUser(userRequest);

    }
}