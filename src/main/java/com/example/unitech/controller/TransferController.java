package com.example.unitech.controller;

import com.example.unitech.dto.request.transfer.TransferRequest;
import com.example.unitech.service.account.AccountTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transfer")
@RequiredArgsConstructor
public class TransferController {

    private final AccountTransferService accountTransferService;

    @PostMapping
    public void transfer(@RequestBody TransferRequest transferRequest){
        accountTransferService.transfer(transferRequest);

    }
}
