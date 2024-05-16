package com.example.unitech.acoountTest;

import com.example.unitech.dto.request.create.AccountCreateRequest;
import com.example.unitech.dto.response.create.AccountCreateResponse;
import com.example.unitech.entity.Account;
import com.example.unitech.entity.User;
import com.example.unitech.enums.Status;
import com.example.unitech.exception.UserExistsException;
import com.example.unitech.mapper.AccountMapper;
import com.example.unitech.repository.AccountRepository;
import com.example.unitech.repository.UserRepository;
import com.example.unitech.service.account.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.math.BigDecimal;
import java.util.Optional;

import static com.example.unitech.enums.Status.ACTIVE;
import static com.example.unitech.enums.Status.INACTIVE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountService accountService;

//    @Captor
//    private ArgumentCaptor<Account> accountCaptor;

    public AccountCreateRequest accountCreateRequest(){
        return AccountCreateRequest.builder()
                .accountNumber(1234567L)
                .status(ACTIVE)
                .balance(BigDecimal.ZERO)
                .fkUserId(1L)
                .build();
    }

//    public AccountCreateRequest changeStatus(){
//        return AccountCreateRequest.builder()
//                .accountNumber(1234567L)
//                .status(INACTIVE)
//                .balance(BigDecimal.ZERO)
//                .fkUserId(1L)
//                .build();
//    }

    public AccountCreateResponse accountCreateResponse(){
        return AccountCreateResponse.builder()
                .accountNumber(1234567L)
                .status(ACTIVE)
                .balance(BigDecimal.ZERO)
                .fkUserId(1L)
                .build();
    }

    public User user(){
        return User.builder()
                .username("test_username")
                .password("123Aa//.")
                .id(1L)
                .email("example@gmail.com")
                .pin("12345678")
                .build();
    }

    public Account account(){
        return Account.builder()
                .accountNumber(1234567L)
                .status(Status.INACTIVE)
                .balance(BigDecimal.ZERO)
                .fkUserId(1L)
                .id(1L)
                .build();
    }

    @Test
    public void testAccountService_whenCreateRequest_shouldReturnCreateResponse() {
        AccountCreateRequest request = accountCreateRequest();
        AccountCreateResponse expectedResponse = accountCreateResponse();
        User user = user();
        Account account = account();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(accountRepository.findByAccountNumber(1234567L)).thenReturn(account());
        when(accountRepository.save(account())).thenReturn(account());
        when(accountMapper.mapToResponse(account)).thenReturn(expectedResponse);

        // Act
        AccountCreateResponse response = accountService.changeStatusAccount(request);
        assertNotNull(response);
        assertEquals(expectedResponse.getStatus(), response.getStatus());
        assertEquals(expectedResponse, response);

        verify(userRepository).findById(request.getFkUserId());
        verify(accountRepository).findByAccountNumber(request.getAccountNumber());
        verify(accountMapper).mapToResponse(account);



//        verify(accountRepository).save(accountCaptor.capture());
//        Account capturedAccount = accountCaptor.getValue();
//        assertEquals(Status.INACTIVE, capturedAccount.getStatus());
//        assertEquals(expectedResponse, response);




    }


    @Test
    public void testAccountService_whenAccountAlreadyExists_shouldThrowException() {
        AccountCreateRequest request = accountCreateRequest();
        User user = user();
        Account account = account();

        when(userRepository.findById(request.getFkUserId())).thenReturn(Optional.of(user));
        when(accountRepository.findByAccountNumber(request.getAccountNumber())).thenReturn(account);

        assertThrows(UserExistsException.class, () -> accountService.changeStatusAccount(request));

        verify(userRepository).findById(request.getFkUserId());
    }


}
