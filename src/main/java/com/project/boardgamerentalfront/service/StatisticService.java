package com.project.boardgamerentalfront.service;

import com.project.boardgamerentalfront.domain.Statistic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class StatisticService {
    private final RestTemplate restTemplate = new RestTemplate();
    private Statistic statistics;
    private static StatisticService statisticService;

    private StatisticService() {
        Statistic statistic = new Statistic();
        statistic.setNumberOfUsers(25);
        statistic.setNumberOfGames(50);
        statistic.setNumberOfAllRents(60);
        statistic.setNumberOfLastMonthRents(30);
        statistic.setNumberOfLastWeekRents(16);
        statistic.setAmountOfAllEarnedMoney(152);
        statistic.setAmountOfLastMonthEarnedMoney(80);
        statistic.setAmountOfLastWeekEarnedMoney(60);
        statistics = statistic;
    }

    public static StatisticService getInstance() {
        if (statisticService == null) {
            statisticService = new StatisticService();
        }
        return statisticService;
    }

    public Statistic getStatistics() {
        return statistics;
    }

    public int getNumberOfUsers() {
        ResponseEntity<Integer> rs = restTemplate.getForEntity("http://localhost:8080/v1/statistic/numberOfUsers",
                Integer.class);
        return rs.getBody();
    }

    public int getNumberOfGames() {
        ResponseEntity<Integer> rs = restTemplate.getForEntity("http://localhost:8080/v1/statistic/numberOfGames",
                Integer.class);
        return rs.getBody();
    }

    public int getNumberOfAllRents() {
        ResponseEntity<Integer> rs = restTemplate.getForEntity("http://localhost:8080/v1/statistic/numberOfAllRents",
                Integer.class);
        return rs.getBody();
    }

    public int getNumberOfLastMonthRents() {
        ResponseEntity<Integer> rs = restTemplate.getForEntity("http://localhost:8080/v1/statistic/numberOfLastMonthRents",
                Integer.class);
        return rs.getBody();
    }

    public int getNumberOfLastWeekRents() {
        ResponseEntity<Integer> rs = restTemplate.getForEntity("http://localhost:8080/v1/statistic/numberOfLastWeekRents",
                Integer.class);
        return rs.getBody();
    }

    public double getAmountOfAllEarnedMoney() {
        ResponseEntity<Double> rs = restTemplate.getForEntity("http://localhost:8080/v1/statistic/numberOfAllEarnedMoney",
                Double.class);
        return rs.getBody();
    }

    public double getAmountOfLastMonthEarnedMoney() {
        ResponseEntity<Double> rs = restTemplate.getForEntity("http://localhost:8080/v1/statistic/numberOfLastMonthEarnedMoney",
                Double.class);
        return rs.getBody();
    }

    public double getAmountOfLastWeekEarnedMoney() {
        ResponseEntity<Double> rs = restTemplate.getForEntity("http://localhost:8080/v1/statistic/numberOfLastWeekEarnedMoney",
                Double.class);
        return rs.getBody();
    }
}