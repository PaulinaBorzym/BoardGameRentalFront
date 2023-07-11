package com.project.boardgamerentalfront.domain;

import com.project.boardgamerentalfront.service.GameService;
import com.project.boardgamerentalfront.service.UserService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Rent {
    private User user;
    private Game game;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;


    public Rent() {
    }

    public Rent(User user, Game game, LocalDate startDate, LocalDate endDate) {
        this.user = user;
        this.game = game;
        this.startDate = startDate;
        this.endDate = endDate;
        price = game.getPrice()* Double.valueOf(ChronoUnit.DAYS.between(startDate,endDate));
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
