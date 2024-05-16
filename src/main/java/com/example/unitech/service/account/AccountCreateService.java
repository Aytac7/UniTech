package com.example.unitech.service.account;
import com.example.unitech.dto.request.create.AccountCreateRequest;
import com.example.unitech.dto.response.create.AccountCreateResponse;
import com.example.unitech.entity.Account;
import com.example.unitech.entity.User;
import com.example.unitech.exception.UserExistsException;
import com.example.unitech.exception.UserNotFoundException;
import com.example.unitech.mapper.AccountMapper;
import com.example.unitech.repository.AccountRepository;
import com.example.unitech.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
public class AccountCreateService {
    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountCreateResponse createAccount(AccountCreateRequest accountRequest){

        log.info("ActionLog.createAccount start");


        Account accountNumber=accountRepository.findByAccountNumber(accountRequest.getAccountNumber());
        if (Objects.nonNull(accountNumber)){
            throw new UserExistsException(HttpStatus.CONFLICT.name(), "Card number already exists other user");
        }
        User user=userRepository.findById(accountRequest.getFkUserId()).orElseThrow(()->new UserNotFoundException(HttpStatus.NOT_FOUND.name(),"user not found"));
        if(user!=null){
           Account mapToAccount=accountMapper.mapToAccount(accountRequest);
           Account account=accountRepository.save(mapToAccount);

            log.info("ActionLog.createAccount start");

            return accountMapper.mapToResponse(account);
        }
        throw new UserNotFoundException(HttpStatus.NOT_FOUND.name(),"user not found");
        }
    }

