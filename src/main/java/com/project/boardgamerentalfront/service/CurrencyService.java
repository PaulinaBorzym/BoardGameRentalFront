package com.project.boardgamerentalfront.service;

import com.project.boardgamerentalfront.domain.Game;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Set;

public class CurrencyService {
    private final RestTemplate restTemplate = new RestTemplate();

    private static CurrencyService currencyService;

    private CurrencyService(){
    }

    public static CurrencyService getInstance() {
        if (currencyService == null) {
            currencyService = new CurrencyService();
        }
        return currencyService;
    }

    public void changeCurrency(String code){
        if(Objects.isNull(code)){
            code = "PLN";
        }
        restTemplate.getForEntity("http://localhost:8080/v1/currency/"+code, String.class);
    }
}
