package com.example.unitech.currencyTest;

import com.example.unitech.dto.response.currency.CurrencyResponse;
import com.example.unitech.entity.Currency;
import com.example.unitech.exception.IllegalArgumentException;
import com.example.unitech.repository.CurrencyRepository;
import com.example.unitech.service.currency.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)

class CurrencyServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private CurrencyService currencyService;

    private CurrencyResponse validCurrencyResponse;
    private CurrencyResponse invalidCurrencyResponse;

    @BeforeEach
    void setUp() {
        validCurrencyResponse = new CurrencyResponse();
        Map<String, Double> rates = new HashMap<>();
        rates.put("USD", 1.0);
        rates.put("EUR", 0.85);
        validCurrencyResponse.setRates(rates);

        invalidCurrencyResponse = new CurrencyResponse(); // Create a valid but empty response
        invalidCurrencyResponse.setRates(new HashMap<>());

        when(restTemplate.getForObject(anyString(), eq(CurrencyResponse.class)))
                .thenReturn(validCurrencyResponse);
        when(restTemplate.getForObject(anyString(), eq(CurrencyResponse.class)))
                .thenReturn(invalidCurrencyResponse);
    }



    @Test
    void testGetSpecificExchange_WithInvalidResponse() {
        when(restTemplate.getForObject(anyString(), eq(CurrencyResponse.class)))
                .thenReturn(invalidCurrencyResponse);

        assertThrows(IllegalArgumentException.class,
                () -> currencyService.getSpecificExchange("USD", "EUR", LocalDate.now()));
    }


    @Test
    void testGetRecentCurrentData() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneMinuteBefore = now.minusMinutes(1);

        Currency currency = new Currency();
        currency.setUpdatedDate(oneMinuteBefore);

        List<Currency> reminders = List.of(currency);

        when(currencyRepository.findByUpdatedDateBefore(any(LocalDateTime.class))).thenReturn(reminders);

        currencyService.getRecentCurrentData();

        verify(currencyRepository, times(1)).findByUpdatedDateBefore(any(LocalDateTime.class));
        verify(currencyRepository, times(1)).save(currency);
        assertTrue(currency.getUpdatedDate().isAfter(oneMinuteBefore));
    }
}
