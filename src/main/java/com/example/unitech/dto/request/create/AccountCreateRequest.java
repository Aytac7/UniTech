package com.example.unitech.dto.request.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AccountCreateRequest {
    BigDecimal balance;
    Long accountNumber;
    String status;
    Long fkUserId;
}
