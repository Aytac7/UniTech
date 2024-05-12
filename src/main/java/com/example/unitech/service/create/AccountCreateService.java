package com.example.unitech.service.create;
import com.example.unitech.dto.request.create.AccountCreateRequest;
import com.example.unitech.dto.response.create.AccountCreateResponse;
import com.example.unitech.entity.Account;
import com.example.unitech.entity.User;
import com.example.unitech.exception.UserNotFoundException;
import com.example.unitech.mapper.AccountMapper;
import com.example.unitech.repository.AccountRepository;
import com.example.unitech.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountCreateService {
    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountCreateResponse createAccount(AccountCreateRequest accountRequest){
        User user=userRepository.findById(accountRequest.getFkUserId()).orElseThrow(()->new UserNotFoundException(HttpStatus.NOT_FOUND.name(),"user not found"));
        if(user!=null){
           Account mapToAccount=accountMapper.mapToAccount(accountRequest);
           Account account=accountRepository.save(mapToAccount);
            return accountMapper.mapToResponse(account);
        }
        throw new UserNotFoundException(HttpStatus.NOT_FOUND.name(),"user not found");
        }
    }

