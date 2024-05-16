package com.example.unitech.userTest;

import com.example.unitech.dto.request.create.UserCreateRequest;
import com.example.unitech.dto.response.create.UserCreateResponse;
import com.example.unitech.entity.User;
import com.example.unitech.exception.UserExistsException;
import com.example.unitech.mapper.UserMapper;
import com.example.unitech.repository.UserRepository;
import com.example.unitech.service.email.EmailService;
import com.example.unitech.service.user.UserCreateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class UserCreateServiceTest {

    @Mock
    private  UserRepository userRepository;

    @Mock
    private  UserMapper userMapper;

    @Mock
    private  EmailService emailService;

    @InjectMocks
    private UserCreateService userCreateService;

    public UserCreateRequest userCreateRequest(){
        return UserCreateRequest.builder()
                .username("test_username")
                .password("123Aa//.")
                .email("example@gmail.com")
                .pin("12345678")
                .build();
    }

    public User createUser() {
        return User.builder()
                .username("test_username")
                .password("123Aa//.")
                .email("example@gmail.com")
                .pin("12345678")
                .build();
    }

    public UserCreateResponse createUserResponse() {
        return UserCreateResponse.builder()
                .username("test_username")
                .email("example@gmail.com")
                .build();


    }

    @Test
    public void testCreateUser_whenUserDoesNotExist_shouldCreateUser(){
        UserCreateRequest request=userCreateRequest();
        User user=createUser();
        UserCreateResponse response=createUserResponse();

        when(userRepository.findByPin(request.getPin())).thenReturn(null);
        when(userMapper.mapToUser(request)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.mapToUserCreateResponse(user)).thenReturn(response);

        UserCreateResponse result=userCreateService.createUser(request);
        assertEquals(response,result);

        verify(userRepository).findByPin(request.getPin());
        verify(userMapper).mapToUser(request);
        verify(userRepository).save(user);
        verify(userMapper).mapToUserCreateResponse(user);
        verify(emailService).sendEmail(user.getEmail(),"Registration is completed","Your registration has been successfully completed. Thank you!");
    }

    @Test
    public void testCreateUser_whenUserExists_shouldThrowException() {
        UserCreateRequest request=userCreateRequest();
        User user=createUser();

        when(userRepository.findByPin(request.getPin())).thenReturn(user);

        assertThrows(UserExistsException.class,()->userCreateService.createUser(request));

        verify(userRepository).findByPin(request.getPin());
        verify(userMapper,never()).mapToUser(request);
        verify(userRepository,never()).save(user);
        verify(userMapper,never()).mapToUserCreateResponse(user);
        verify(emailService,never()).sendEmail(anyString(), anyString(), anyString());

    }



}
