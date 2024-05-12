package com.example.unitech.dto.response.currency;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CurrencyResponse {
    private Long id;
    private String currencyType;
    private Map<String, Double> rates;
    //CurrencyType, exchange range
}
