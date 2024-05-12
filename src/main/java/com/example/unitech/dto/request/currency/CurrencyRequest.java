package com.example.unitech.dto.request.currency;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CurrencyRequest {
    private Long id;
    private String currencyType;
    private BigDecimal rate;

}
