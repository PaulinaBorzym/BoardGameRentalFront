package com.project.boardgamerentalfront.domain;

import com.project.boardgamerentalfront.enums.GameType;
import java.util.Objects;

public class Game {
    private Long gameId;
    private String title;
    private double price;
    private String publicationYear;
    private GameType type;
    private String description;

    public Game() {
    }

    public Game(Long gameId, String title, double price, String publicationYear, GameType type, String description) {
        this.gameId = gameId;
        this.title = title;
        this.price = price;
        this.publicationYear = publicationYear;
        this.type = type;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Long getGameId() {
        return gameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Double.compare(game.price, price) == 0 && Objects.equals(title, game.title) &&
                Objects.equals(publicationYear, game.publicationYear) && type == game.type;
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

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    @Override
    public String toString() {
        return title;
    }
}