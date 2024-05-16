package com.example.unitech.controller;

import com.example.unitech.dto.response.currency.CurrencyResponse;
import com.example.unitech.service.currency.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currencies")
public class CurrencyController {
    private final CurrencyService currencyService;

    @GetMapping("/latest")
    public ResponseEntity<CurrencyResponse>currencyRates(){
        return ResponseEntity.status(HttpStatus.OK).body(currencyService.allCurrencyRates());
    }

    @GetMapping("/convert")
    public ResponseEntity<Double>convertAmount(@RequestParam Double amount, @RequestParam String source,
                                               @RequestParam String target, @RequestParam LocalDate date){
        return ResponseEntity.status(HttpStatus.OK).body(currencyService.convertAmount(amount, source, target, date));
    }

    @GetMapping("/specific")
    public ResponseEntity<Double> getExchangeRate(@RequestParam String source, @RequestParam String target,
                                                  @RequestParam LocalDate date){
        return ResponseEntity.status(HttpStatus.OK).body(currencyService.getSpecificExchange(source, target, date));
    }

}
