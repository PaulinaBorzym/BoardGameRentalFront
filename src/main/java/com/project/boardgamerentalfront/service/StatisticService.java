package com.project.boardgamerentalfront.service;

import com.project.boardgamerentalfront.domain.Game;
import com.project.boardgamerentalfront.domain.Statistic;

import java.util.HashSet;
import java.util.Set;

public class StatisticService {
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
        return statistics.getNumberOfUsers();
    }
    public int getNumberOfGames() {
        return statistics.getNumberOfGames();
    }
    public int getNumberOfAllRents() {
        return statistics.getNumberOfAllRents();
    }
    public int getNumberOfLastMonthRents() {
        return statistics.getNumberOfLastMonthRents();
    }
    public int getNumberOfLastWeekRents() {
        return statistics.getNumberOfLastWeekRents();
    }
    public double getAmountOfAllEarnedMoney() {
        return statistics.getAmountOfAllEarnedMoney();
    }
    public double getAmountOfLastMonthEarnedMoney() {
        return statistics.getAmountOfLastMonthEarnedMoney();
    }
    public double getAmountOfLastWeekEarnedMoney() {
        return statistics.getAmountOfLastWeekEarnedMoney();
    }

}
