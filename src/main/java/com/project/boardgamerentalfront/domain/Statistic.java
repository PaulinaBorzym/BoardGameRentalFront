package com.project.boardgamerentalfront.domain;

import java.util.Objects;

public class Statistic {
    private int numberOfUsers;
    private int numberOfGames;
    private int numberOfAllRents;
    private int numberOfLastMonthRents;
    private int numberOfLastWeekRents;
    private double amountOfAllEarnedMoney;
    private double amountOfLastMonthEarnedMoney;
    private double amountOfLastWeekEarnedMoney;

    public Statistic() {
    }

    public Statistic(int numberOfUsers, int numberOfGames, int numberOfAllRents, int numberOfLastMonthRents,
                     int numberOfLastWeekRents, double amountOfAllEarnedMoney, double amountOfLastMonthEarnedMoney,
                     double amountOfLastWeekEarnedMoney) {
        this.numberOfUsers = numberOfUsers;
        this.numberOfGames = numberOfGames;
        this.numberOfAllRents = numberOfAllRents;
        this.numberOfLastMonthRents = numberOfLastMonthRents;
        this.numberOfLastWeekRents = numberOfLastWeekRents;
        this.amountOfAllEarnedMoney = amountOfAllEarnedMoney;
        this.amountOfLastMonthEarnedMoney = amountOfLastMonthEarnedMoney;
        this.amountOfLastWeekEarnedMoney = amountOfLastWeekEarnedMoney;
    }

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setNumberOfUsers(int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public int getNumberOfAllRents() {
        return numberOfAllRents;
    }

    public void setNumberOfAllRents(int numberOfAllRents) {
        this.numberOfAllRents = numberOfAllRents;
    }

    public int getNumberOfLastMonthRents() {
        return numberOfLastMonthRents;
    }

    public void setNumberOfLastMonthRents(int numberOfLastMonthRents) {
        this.numberOfLastMonthRents = numberOfLastMonthRents;
    }

    public int getNumberOfLastWeekRents() {
        return numberOfLastWeekRents;
    }

    public void setNumberOfLastWeekRents(int numberOfLastWeekRents) {
        this.numberOfLastWeekRents = numberOfLastWeekRents;
    }

    public double getAmountOfAllEarnedMoney() {
        return amountOfAllEarnedMoney;
    }

    public void setAmountOfAllEarnedMoney(double amountOfAllEarnedMoney) {
        this.amountOfAllEarnedMoney = amountOfAllEarnedMoney;
    }

    public double getAmountOfLastMonthEarnedMoney() {
        return amountOfLastMonthEarnedMoney;
    }

    public void setAmountOfLastMonthEarnedMoney(double amountOfLastMonthEarnedMoney) {
        this.amountOfLastMonthEarnedMoney = amountOfLastMonthEarnedMoney;
    }

    public double getAmountOfLastWeekEarnedMoney() {
        return amountOfLastWeekEarnedMoney;
    }

    public void setAmountOfLastWeekEarnedMoney(double amountOfLastWeekEarnedMoney) {
        this.amountOfLastWeekEarnedMoney = amountOfLastWeekEarnedMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistic statistic = (Statistic) o;
        return numberOfUsers == statistic.numberOfUsers &&
                numberOfGames == statistic.numberOfGames &&
                numberOfAllRents == statistic.numberOfAllRents &&
                numberOfLastMonthRents == statistic.numberOfLastMonthRents &&
                numberOfLastWeekRents == statistic.numberOfLastWeekRents &&
                Double.compare(statistic.amountOfAllEarnedMoney, amountOfAllEarnedMoney) == 0 &&
                Double.compare(statistic.amountOfLastMonthEarnedMoney, amountOfLastMonthEarnedMoney) == 0 &&
                Double.compare(statistic.amountOfLastWeekEarnedMoney, amountOfLastWeekEarnedMoney) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfUsers, numberOfGames, numberOfAllRents, numberOfLastMonthRents,
                numberOfLastWeekRents, amountOfAllEarnedMoney, amountOfLastMonthEarnedMoney, amountOfLastWeekEarnedMoney);
    }
}