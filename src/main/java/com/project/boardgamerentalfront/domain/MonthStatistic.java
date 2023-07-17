package com.project.boardgamerentalfront.domain;

import java.util.Objects;

public class MonthStatistic {
    private Long statisticId;
    private String month;
    private String year;
    private int numberOfUsers;
    private int numberOfGames;
    private int numberOfAllRents;
    private int numberOfLastMonthRents;
    private double amountOfLastMonthEarnedMoney;

    public MonthStatistic() {
    }

    public MonthStatistic(String month, String year, int numberOfUsers, int numberOfGames, int numberOfAllRents, int numberOfLastMonthRents, double amountOfLastMonthEarnedMoney) {
        this.month = month;
        this.year = year;
        this.numberOfUsers = numberOfUsers;
        this.numberOfGames = numberOfGames;
        this.numberOfAllRents = numberOfAllRents;
        this.numberOfLastMonthRents = numberOfLastMonthRents;
        this.amountOfLastMonthEarnedMoney = amountOfLastMonthEarnedMoney;
    }

    public Long getStatisticId() {
        return statisticId;
    }

    public void setStatisticId(Long statisticId) {
        this.statisticId = statisticId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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

    public double getAmountOfLastMonthEarnedMoney() {
        return amountOfLastMonthEarnedMoney;
    }

    public void setAmountOfLastMonthEarnedMoney(double amountOfLastMonthEarnedMoney) {
        this.amountOfLastMonthEarnedMoney = amountOfLastMonthEarnedMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthStatistic that = (MonthStatistic) o;
        return numberOfUsers == that.numberOfUsers && numberOfGames == that.numberOfGames &&
                numberOfAllRents == that.numberOfAllRents && numberOfLastMonthRents == that.numberOfLastMonthRents &&
                Double.compare(that.amountOfLastMonthEarnedMoney, amountOfLastMonthEarnedMoney) == 0 &&
                Objects.equals(statisticId, that.statisticId) && Objects.equals(month, that.month) &&
                Objects.equals(year, that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statisticId, month, year, numberOfUsers, numberOfGames, numberOfAllRents,
                numberOfLastMonthRents, amountOfLastMonthEarnedMoney);
    }

    @Override
    public String toString() {
        return "MonthStatistic{" +
                "statisticId=" + statisticId +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", numberOfUsers=" + numberOfUsers +
                ", numberOfGames=" + numberOfGames +
                ", numberOfAllRents=" + numberOfAllRents +
                ", numberOfLastMonthRents=" + numberOfLastMonthRents +
                ", amountOfLastMonthEarnedMoney=" + amountOfLastMonthEarnedMoney +
                '}';
    }
}