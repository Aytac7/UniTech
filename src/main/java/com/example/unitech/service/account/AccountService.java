package com.example.unitech.service.account;

import com.example.unitech.dto.request.create.AccountCreateRequest;
import com.example.unitech.dto.response.create.AccountCreateResponse;
import com.example.unitech.entity.Account;
import com.example.unitech.entity.User;
import com.example.unitech.enums.Status;
import com.example.unitech.exception.AccountNotFoundException;
import com.example.unitech.exception.UserNotFoundException;
import com.example.unitech.mapper.AccountMapper;
import com.example.unitech.repository.AccountRepository;
import com.example.unitech.repository.UserRepository;
import com.example.unitech.wrapper.AccountWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountCreateResponse changeStatusAccount( AccountCreateRequest request) {

        log.info("ActionLog.changeStatusAccount start");

        User user = userRepository.findById(request.getFkUserId()).orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND.name(),"USER_NOT_FOUND"));
        Account account = accountRepository.findByAccountNumber(request.getAccountNumber());
        account.setStatus(Status.INACTIVE);
        log.info("ActionLog.changeStatusAccount end");
        return accountMapper.mapToResponse(accountRepository.save(account));
    }

    public List<AccountWrapper> allActiveAccounts(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND.name(), "USER_NOT_FOUND"));
        List<AccountWrapper> accountResponses = accountRepository.allActiveAccountByUserId(user.getId());

        log.info("ActionLog.changeStatusAccount end");

        return accountResponses ;
    }

}
