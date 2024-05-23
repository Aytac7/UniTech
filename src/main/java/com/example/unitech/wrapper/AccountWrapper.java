package com.example.unitech.wrapper;
import com.example.unitech.enums.Status;
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
        private Status status;
        private BigDecimal balance;

}
