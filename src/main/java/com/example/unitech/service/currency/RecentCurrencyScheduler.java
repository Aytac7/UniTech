package com.example.unitech.service.currency;

import com.example.unitech.service.currency.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecentCurrencyScheduler {

    private final CurrencyService currencyService;

    @Scheduled(cron = "0/30 * * * * *")
    public void recentCurrencyData(){
        log.info("ActionLog.recentCurrencyData start: ");
        currencyService.getRecentCurrentData();
        log.info("ActionLog.recentCurrencyData end: ");
    }
}
