package com.project.boardgamerentalfront.domain;

import java.util.Objects;

public class Game {
    private String title;
    private double price;
    private String publicationYear;
    private GameType type;

    public Game() {
    }

    public Game(String title, double price, String publicationYear, GameType type) {
        this.title = title;
        this.price = price;
        this.publicationYear = publicationYear;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public GameType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Double.compare(game.price, price) == 0 && Objects.equals(title, game.title) && Objects.equals(publicationYear, game.publicationYear) && type == game.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, publicationYear, type);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setType(GameType type) {
        this.type = type;
    }
}
