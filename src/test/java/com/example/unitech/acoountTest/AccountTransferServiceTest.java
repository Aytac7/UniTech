package com.example.unitech.acoountTest;

import com.example.unitech.dto.request.transfer.TransferRequest;
import com.example.unitech.entity.Account;
import com.example.unitech.enums.Status;
import com.example.unitech.exception.AccountActivationException;
import com.example.unitech.exception.InsufficientBalanceException;
import com.example.unitech.exception.SameAccountException;
import com.example.unitech.repository.AccountRepository;
import com.example.unitech.service.account.AccountTransferService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class AccountTransferServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountTransferService accountTransferService;

    private TransferRequest createTransferRequest(Long senderId, Long receiverId, BigDecimal amount) {
        return TransferRequest.builder()
                .senderId(senderId)
                .receiverId(receiverId)
                .amount(amount)
                .build();
    }

    private Account createAccount(long accountNumber, BigDecimal balance, Status status) {
        return Account.builder()
                .accountNumber(accountNumber)
                .balance(balance)
                .status(status)
                .build();
    }

    @Test
    public void testTransfer_whenSuccessful() {
        TransferRequest request = createTransferRequest(1234567890L, 89012345L, new BigDecimal(100));
        Account fromAccount = createAccount(1234567890L, new BigDecimal(200), Status.ACTIVE);
        Account toAccount = createAccount(89012345L, new BigDecimal(50), Status.ACTIVE);

        when(accountRepository.findByAccountNumber(1234567890L)).thenReturn(fromAccount);
        when(accountRepository.findByAccountNumber(89012345L)).thenReturn(toAccount);

        accountTransferService.transfer(request);

        assertEquals(new BigDecimal(100), fromAccount.getBalance());
        assertEquals(new BigDecimal(150), toAccount.getBalance());

        verify(accountRepository, times(1)).saveAll(List.of(fromAccount, toAccount));
    }


    @Test
    public void testTransfer_whenSameAccount_shouldThrowException() {
        TransferRequest request=createTransferRequest(1234567L,1234567L,new BigDecimal(100));
        Account account=createAccount(1234567L,new BigDecimal(200),Status.ACTIVE);

        when(accountRepository.findByAccountNumber(1234567L)).thenReturn(account);

        assertThrows(SameAccountException.class,()->accountTransferService.transfer(request));
        verify(accountRepository,never()).saveAll(any());
    }

    @Test
    public void testTransfer_whenFromAccountInactive_shouldThrowException() {

        TransferRequest request=createTransferRequest(1234567L,8901234L,new BigDecimal(100));
        Account fromAccount=createAccount(1234567L,new BigDecimal(200),Status.INACTIVE);
        Account toAccount=createAccount(8901234L,new BigDecimal(50),Status.ACTIVE);

        when(accountRepository.findByAccountNumber(1234567L)).thenReturn(fromAccount);
        when(accountRepository.findByAccountNumber(8901234L)).thenReturn(toAccount);

        assertThrows(AccountActivationException.class,()->accountTransferService.transfer(request));
        verify(accountRepository,never()).saveAll(any());
    }

    @Test
    public void testTransfer_whenToAccountInactive_shouldThrowException() {
        TransferRequest request=createTransferRequest(1234567L,8901234L,new BigDecimal(100));
        Account fromAccount=createAccount(1234567L,new BigDecimal(200),Status.ACTIVE);
        Account toAccount=createAccount(8901234L,new BigDecimal(50),Status.INACTIVE);

        when(accountRepository.findByAccountNumber(1234567L)).thenReturn(fromAccount);
        when(accountRepository.findByAccountNumber(8901234L)).thenReturn(toAccount);

        assertThrows(AccountActivationException.class,()->accountTransferService.transfer(request));
        verify(accountRepository,never()).saveAll(any());
    }

    @Test
    public void testTransfer_whenInsufficientBalance_shouldThrowException() {
        TransferRequest request=createTransferRequest(1234567L,8901234L,new BigDecimal(300));
        Account fromAccount=createAccount(1234567L,new BigDecimal(200),Status.ACTIVE);
        Account toAccount=createAccount(8901234L,new BigDecimal(50),Status.ACTIVE);

        when(accountRepository.findByAccountNumber(1234567L)).thenReturn(fromAccount);
        when(accountRepository.findByAccountNumber(8901234L)).thenReturn(toAccount);

        assertThrows(InsufficientBalanceException.class,()->accountTransferService.transfer(request));
        verify(accountRepository,never()).saveAll(any());
    }

    @Test
    public void testTransfer_whenBalanceZero_shouldThrowException() {
        TransferRequest request=createTransferRequest(1234567L,8901234L,new BigDecimal(100));
        Account fromAccount=createAccount(1234567L,BigDecimal.ZERO,Status.ACTIVE);
        Account toAccount=createAccount(8901234L,new BigDecimal(50),Status.ACTIVE);

        when(accountRepository.findByAccountNumber(1234567L)).thenReturn(fromAccount);
        when(accountRepository.findByAccountNumber(8901234L)).thenReturn(toAccount);

        assertThrows(InsufficientBalanceException.class,()->accountTransferService.transfer(request));
        verify(accountRepository,never()).saveAll(any());
    }

    }
