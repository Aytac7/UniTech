package com.example.unitech.service;

import com.example.unitech.dto.request.transfer.TransferRequest;
import com.example.unitech.entity.Account;
import com.example.unitech.exception.AccountActivationException;
import com.example.unitech.exception.InsufficientBalanceException;
import com.example.unitech.exception.SameAccountException;
import com.example.unitech.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountTransferService {

    private final AccountRepository accountRepository;

    public void transfer(TransferRequest transferRequest){
        Account fromAccount=accountRepository.findByAccountNumber(transferRequest.getSenderId());
        Account toAccount=accountRepository.findByAccountNumber(transferRequest.getReceiverId());

        if(fromAccount.getAccountNumber().equals(toAccount.getAccountNumber())){
            throw new SameAccountException(HttpStatus.BAD_REQUEST.name(), "your account same");
        }
        if(fromAccount.getStatus()!="A"){
            throw new AccountActivationException(HttpStatus.BAD_REQUEST.name(), "your status is deactivate");
        }
        if(toAccount.getStatus()!="A"){
            throw new AccountActivationException(HttpStatus.BAD_REQUEST.name(), "your status is deactivate");
        }
        if(Objects.equals(fromAccount.getBalance(), BigDecimal.ZERO)){
            throw new InsufficientBalanceException(HttpStatus.NOT_FOUND.name(),"you don't have enough balance");
        }
        else if (fromAccount.getBalance().compareTo(transferRequest.getAmount())<0)  {
            throw new InsufficientBalanceException(HttpStatus.NOT_FOUND.name(),"you don't have enough balance");
        }
        fromAccount.setBalance(fromAccount.getBalance().subtract(transferRequest.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(transferRequest.getAmount()));
        accountRepository.saveAll(List.of(fromAccount,toAccount));
    }
}
