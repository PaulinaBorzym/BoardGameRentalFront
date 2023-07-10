package com.project.boardgamerentalfront.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GameService {
    private Set<Game> games;
    private static GameService gameService;

    private GameService() {
        this.games = exampleData();
    }

    public static GameService getInstance() {
        if (gameService == null) {
            gameService = new GameService();
        }
        return gameService;
    }

    public Set<Game> getGames() {
        return new HashSet<>(games);
    }

    public void addGames(Game game) {
        this.games.add(game);
    }

    private Set<Game> exampleData() {
        Set<Game> games = new HashSet<>();
        games.add(new Game("Kalambury", 10,  "2015", GameType.FAMILY));
        games.add(new Game("Grzybobranie",10, "2009", GameType.FOR_KIDS));
        games.add(new Game("PartyTime", 15, "2012", GameType.PARTY));
        games.add(new Game("I know", 20, "2012", GameType.FAMILY));
        games.add(new Game("Na skrzydłach", 20, "2019", GameType.STRATEGIC));
        games.add(new Game("Pojedynek", 15, "2019", GameType.STRATEGIC));
        games.add(new Game("Monopol", 10, "2019", GameType.FAMILY));
        return games;
    }

    public Set<Game> findByTitle(String title) {
        return games.stream().filter(game -> game.getTitle().contains(title))
                .collect(Collectors.toSet());
    }

    public void save(Game game) {
        this.games.add(game);
    }

    public void delete(Game game) {
        this.games.remove(game);
    }
}