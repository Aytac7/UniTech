package com.example.unitech.acoountTest;

import com.example.unitech.dto.request.create.AccountCreateRequest;
import com.example.unitech.dto.response.create.AccountCreateResponse;
import com.example.unitech.entity.Account;
import com.example.unitech.entity.User;
import com.example.unitech.enums.Status;
import com.example.unitech.exception.UserExistsException;
import com.example.unitech.mapper.AccountMapper;
import com.example.unitech.repository.AccountRepository;

import static com.example.unitech.enums.Status.ACTIVE;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.unitech.repository.UserRepository;
import com.example.unitech.service.account.AccountCreateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class AccountCreateServiceTest {

    @Mock
    private  AccountRepository accountRepository;

    @Mock
    private  UserRepository userRepository;

    @Mock
    private  AccountMapper accountMapper;

    @InjectMocks
    private AccountCreateService accountCreateService;


    public AccountCreateRequest accountCreateRequest() {
        return AccountCreateRequest.builder()
                .accountNumber(123457L)
                .status(ACTIVE)
                .balance(BigDecimal.ZERO)
                .fkUserId(1L)
                .build();
    }

    public AccountCreateResponse accountCreateResponse() {
        return AccountCreateResponse.builder()
                .accountNumber(123457L)
                .status(ACTIVE)
                .balance(BigDecimal.ZERO)
                .fkUserId(1L)
                .build();
    }

    public User user() {
        return User.builder()
                .username("test_username")
                .password("123Aa//.")
                .id(1L)
                .email("example@gmail.com")
                .pin("12345678")
                .build();
    }

    public Account account() {
        return Account.builder()
                .accountNumber(123457L)
                .status(ACTIVE)
                .balance(BigDecimal.ZERO)
                .fkUserId(1L)
                .id(1L)
                .build();
    }


    @Test
    public void testCreateAccount_whenCreateRequest_shouldReturnCreateResponse() {
        // Arrange
        AccountCreateResponse expectedResponse = accountCreateResponse();
        AccountCreateRequest request = accountCreateRequest();
        User user = user();
        Account account = account();

        when(accountRepository.findByAccountNumber(request.getAccountNumber())).thenReturn(null);
        when(userRepository.findById(request.getFkUserId())).thenReturn(Optional.of(user));
        when(accountMapper.mapToAccount(request)).thenReturn(account);
        when(accountRepository.save(account)).thenReturn(account);
        when(accountMapper.mapToResponse(account)).thenReturn(expectedResponse);

        // Act
        AccountCreateResponse actualResponse = accountCreateService.createAccount(request);

        // Assert
        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getAccountNumber(), actualResponse.getAccountNumber());

        // Verify
        verify(accountRepository).findByAccountNumber(request.getAccountNumber());
        verify(userRepository).findById(request.getFkUserId());
        verify(accountMapper).mapToAccount(request);
        verify(accountRepository).save(account);
        verify(accountMapper).mapToResponse(account);

        // Test exception for duplicate account number
        when(accountRepository.findByAccountNumber(request.getAccountNumber())).thenReturn(account);
        assertThrows(UserExistsException.class, () -> accountCreateService.createAccount(request));

        // Verify exception case (optional)
        verify(accountRepository, times(2)).findByAccountNumber(request.getAccountNumber());

}

    }



