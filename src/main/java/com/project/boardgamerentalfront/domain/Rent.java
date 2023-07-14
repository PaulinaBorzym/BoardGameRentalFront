package com.project.boardgamerentalfront.domain;

import com.project.boardgamerentalfront.service.GameService;
import com.project.boardgamerentalfront.service.UserService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Rent {
    private Long rentId;
    private User user;
    private Game game;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;


    public Rent() {
    }

    public Rent(Long rentId,User user, Game game, LocalDate startDate, LocalDate endDate) {
        this.rentId = rentId;
        this.user = user;
        this.game = game;
        this.startDate = startDate;
        this.endDate = endDate;
        price = game.getPrice()* Double.valueOf(ChronoUnit.DAYS.between(startDate,endDate));
    }

    public Long getRentId() {
        return rentId;
    }

    public void setRentId(Long rentId) {
        this.rentId = rentId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rent rent = (Rent) o;
        return Double.compare(rent.price, price) == 0 && Objects.equals(user, rent.user) && Objects.equals(game, rent.game) && Objects.equals(startDate, rent.startDate) && Objects.equals(endDate, rent.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, game, startDate, endDate, price);
    }
}
