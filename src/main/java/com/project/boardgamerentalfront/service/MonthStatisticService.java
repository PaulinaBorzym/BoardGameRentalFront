package com.project.boardgamerentalfront.service;

import com.project.boardgamerentalfront.domain.MonthStatistic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class MonthStatisticService {
    private final RestTemplate restTemplate = new RestTemplate();
    private static MonthStatisticService monthStatisticService;

    public MonthStatisticService() {
    }

    public static MonthStatisticService getInstance() {
        if (monthStatisticService == null) {
            monthStatisticService = new MonthStatisticService();
        }
        return monthStatisticService;
    }

    public Set<MonthStatistic> getMonthStatistic() {
        ResponseEntity<MonthStatistic[]> rs = restTemplate.getForEntity(
                "http://localhost:8080/v1/statistic/month", MonthStatistic[].class);
        return Arrays.stream(rs.getBody()).collect(Collectors.toSet());
    }
}
