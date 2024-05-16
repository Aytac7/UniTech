package com.example.unitech.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountWrapper {
        private Long id;
        private Long accountNumber;
        private String status;
        private BigDecimal balance;

}
