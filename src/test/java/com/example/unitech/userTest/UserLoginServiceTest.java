package com.example.unitech.userTest;

import com.example.unitech.constant.UniTech;
import com.example.unitech.dto.request.login.ForgotPasswordRequest;
import com.example.unitech.dto.request.login.UserLoginRequest;
import com.example.unitech.dto.response.login.UserLoginResponse;
import com.example.unitech.entity.User;
import com.example.unitech.exception.UserNotFoundException;
import com.example.unitech.mapper.UserMapper;
import com.example.unitech.repository.UserRepository;
import com.example.unitech.service.email.EmailService;
import com.example.unitech.service.user.UserLoginService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class UserLoginServiceTest {

    @Mock
    private  UserRepository userRepository;

    @Mock
    private  UserMapper userMapper;

    @Mock
    private  EmailService emailService;

    @InjectMocks
    private UserLoginService userLoginService;


    UserLoginRequest request = UserLoginRequest.builder()
            .pin("12345678")
            .password("password123")
            .build();
    User user = User.builder()
            .pin("12345678")
            .password("password123")
            .build();
    UserLoginResponse response = UserLoginResponse.builder()
            .username("test_username")
            .build();

    ForgotPasswordRequest requestPassword = ForgotPasswordRequest.builder()
            .email("example@gmail.com")
            .build();

    @Test

    void testLogin_whenUserExistsAndCorrectPassword_shouldReturnLoggedInUser() {
        when(userRepository.findByPin(request.getPin())).thenReturn(user);
        when(userMapper.entityToLoginResponse(user)).thenReturn(response);

        ResponseEntity<?> result=userLoginService.login(request);

        assertEquals(HttpStatus.OK,result.getStatusCode());
        assertEquals(response,result.getBody());

        verify(userRepository).findByPin(request.getPin());
        verify(userMapper).entityToLoginResponse(user);

    }

    @Test
    public void testLogin_whenUserDoesNotExistOrIncorrectPassword_shouldThrowException() {
        when(userRepository.findByPin(request.getPin())).thenReturn(null);

        assertThrows(UserNotFoundException.class,()->userLoginService.login(request));

        verify(userRepository).findByPin(request.getPin());
        verify(userMapper,never()).entityToLoginResponse(user);


        when(userRepository.findByPin(request.getPin())).thenReturn(user);
        assertThrows(UserNotFoundException.class, () -> userLoginService.login(request));
        verify(userRepository).findByPin(request.getPin());
        verify(userMapper, never()).entityToLoginResponse(user);
    }

    @Test
    public void testForgotPassword_whenUserExists_shouldSendEmailAndReturnCheckEmailMessage() throws MessagingException {


        when(userRepository.findByEmailIgnoreCase(requestPassword.getEmail())).thenReturn(Optional.of(user));
        String result=userLoginService.forgotPassword(requestPassword);

        assertEquals(UniTech.CHECK_EMAIL,result);
        verify(emailService).forgetPassword(user.getEmail(),"Check email", user.getPassword());
    }

    @Test
    public void testForgotPassword_whenUserDoesNotExist_shouldReturnUserNotFoundMessage() throws MessagingException {
        when(userRepository.findByEmailIgnoreCase(requestPassword.getEmail())).thenReturn(Optional.empty());
        String result=userLoginService.forgotPassword(requestPassword);

        assertEquals("User not found",result);
        verify(emailService, never()).forgetPassword(anyString(), anyString(), anyString());
    }



    }
