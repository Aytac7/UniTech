package com.example.unitech.dto.response.create;

import com.example.unitech.enums.Status;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountCreateResponse {
    BigDecimal balance;
    Long accountNumber;
    Status status;
    Long fkUserId;

}