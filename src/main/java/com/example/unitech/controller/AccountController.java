package com.example.unitech.controller;

import com.example.unitech.dto.request.create.AccountCreateRequest;
import com.example.unitech.dto.response.create.AccountCreateResponse;
import com.example.unitech.service.account.AccountCreateService;
import com.example.unitech.service.account.AccountService;
import com.example.unitech.wrapper.AccountWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AccountCreateService accountCreateService;

    @PostMapping("/createAccount")
    public ResponseEntity<AccountCreateResponse> createAccount(@RequestBody AccountCreateRequest accountRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountCreateService.createAccount(accountRequest));
    }

    @GetMapping("/allActiveAccounts/{userId}")
    public ResponseEntity<List<AccountWrapper>> allActiveAccounts(@PathVariable(name = "userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.allActiveAccounts(userId));
    }

    @PutMapping("/change-status")
    public ResponseEntity<AccountCreateResponse> changeStatusAccount(@RequestBody AccountCreateRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.changeStatusAccount( request));
    }
}
