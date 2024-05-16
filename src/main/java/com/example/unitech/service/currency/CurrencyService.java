package com.example.unitech.service.currency;
import com.example.unitech.dto.response.currency.CurrencyResponse;
import com.example.unitech.entity.Currency;
import com.example.unitech.exception.IllegalArgumentException;
import com.example.unitech.repository.CurrencyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;




@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyService {

    @Value("${currency.api.url}")
    private String url;


    private final RestTemplate restTemplate;
    private final CurrencyRepository currencyRepository;

    public CurrencyResponse allCurrencyRates(){
        return restTemplate.getForObject(url,CurrencyResponse.class);

    }

    public double getSpecificExchange(String sourceCurrency, String targetSource, LocalDate date){

        log.info("ActionLog.getSpecificExchange start");

        CurrencyResponse response=restTemplate.getForObject(url,CurrencyResponse.class);
        if(response==null){
            throw new IllegalArgumentException(HttpStatus.BAD_REQUEST.name(),"Invalid data");
        }
        double sourceRate=response.getRates().get(sourceCurrency);
        double targetRate=response.getRates().get(targetSource);
        if(sourceRate==0 || targetRate==0 ){
            throw new IllegalArgumentException(HttpStatus.BAD_REQUEST.name(),"Invalid pair");
        }
        Currency currency=new Currency();
        currency.setCurrencyType(sourceCurrency);
        currency.setRate(targetRate/sourceRate);
        currency.setUpdatedDate(LocalDateTime.now());
        currencyRepository.save(currency);

        log.info("ActionLog.getSpecificExchange end");

        return targetRate/sourceRate;
    }

    public double convertAmount(double amount, String source,String target,LocalDate date){

        log.info("ActionLog.convertAmount start");

        double exchangeRate=getSpecificExchange(source,target,date);
        if(exchangeRate==0){
            throw new IllegalArgumentException(HttpStatus.BAD_REQUEST.name(), "Invalid pair");
        }

        log.info("ActionLog.convertAmount end");

        return amount*exchangeRate;
    }

    public void getRecentCurrentData(){

        log.info("ActionLog.getRecentCurrentData start");

        LocalDateTime now=LocalDateTime.now().minusMinutes(1);
        List<Currency>reminders=currencyRepository.findByUpdatedDateBefore(now);
        for(Currency currency: reminders){
            currency.setUpdatedDate(LocalDateTime.now());
            currencyRepository.save(currency);

            log.info("ActionLog.getRecentCurrentData end");

        }
    }


}
