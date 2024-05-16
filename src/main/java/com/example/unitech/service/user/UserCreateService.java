package com.example.unitech.service.user;

import com.example.unitech.dto.request.create.UserCreateRequest;
import com.example.unitech.dto.response.create.UserCreateResponse;
import com.example.unitech.entity.User;

import com.example.unitech.exception.UserExistsException;
import com.example.unitech.mapper.UserMapper;
import com.example.unitech.repository.UserRepository;
import com.example.unitech.service.email.EmailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserCreateService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EmailService emailService;

    @Transactional
    @SneakyThrows
    public UserCreateResponse createUser(UserCreateRequest userRequest){

        log.info("ActionLog.createUser start");

        if(userRepository.findByPin(userRequest.getPin())!=null){
            throw new UserExistsException(HttpStatus.CONFLICT.name(), "User exits already");
        }

           User user= userMapper.mapToUser(userRequest);
           User userEntity= userRepository.save(user);
           UserCreateResponse  userResponse= userMapper.mapToUserCreateResponse(userEntity);
               emailService.sendEmail(user.getEmail(), "Registration is completed", "Your registration has been successfully completed. Thank you!");

        log.info("ActionLog.createUser end");

        return userResponse;
    }



}
