package com.project.boardgamerentalfront.service;

import com.project.boardgamerentalfront.domain.Game;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


public class GameService {
    private final RestTemplate restTemplate = new RestTemplate();
    private Set<Game> games;
    private static GameService gameService;

    private GameService() {
        this.games = getGames();
    }

    public static GameService getInstance() {
        if (gameService == null) {
            gameService = new GameService();
        }
        return gameService;
    }

    public Set<Game> getGames() {
        ResponseEntity<Game[]> rs = restTemplate.getForEntity("http://localhost:8080/v1/games", Game[].class);
        return Arrays.stream(rs.getBody()).collect(Collectors.toSet());
    }

    public Set<Game> findByTitle(String title) {
        return games.stream().filter(game -> game.getTitle().contains(title))
                .collect(Collectors.toSet());
    }

    public void save(Game game) {
        this.games.add(game);
        restTemplate.postForObject("http://localhost:8080/v1/games", game, Game.class);
    }

    public void edit(Game game) {
        restTemplate.put("http://localhost:8080/v1/games", game, Game.class);
    }

    public void delete(Game game) {
        this.games.remove(game);
        restTemplate.delete("http://localhost:8080/v1/games/" + game.getGameId());
    }
}