//package com.example.unitech.feignClient;//package com.example.Unitech.feignClient
//
//import com.example.unitech.feignClient.clientDto.ExchangeRateResponse;
//import org.springframework.cloud.openfeign.FeignClient;
//
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.math.BigDecimal;
//
//@FeignClient(name="ms-currency",url="https://api.fxratesapi.com/latest")
//
// public interface ThirdPartCurrencyServiceClient {
//    ExchangeRateResponse getLatestExchangeRates(
//            @RequestParam String currencies,
//            @RequestParam String base,
//           @RequestParam BigDecimal amount);
//
//
//}