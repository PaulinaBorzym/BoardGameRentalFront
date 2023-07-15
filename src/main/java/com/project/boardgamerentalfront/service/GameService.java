package com.project.boardgamerentalfront.service;

import com.project.boardgamerentalfront.domain.Game;
import com.project.boardgamerentalfront.enums.GameType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;
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
        ResponseEntity<Game[]> rs = restTemplate.getForEntity("http://localhost:8080/v1/games",Game[].class);
        return Arrays.stream(rs.getBody()).collect(Collectors.toSet());
    }


//    private Set<Game> exampleData() {
//        Set<Game> games = new HashSet<>();
//        games.add(new Game(2L,"Kalambury", 10,  "2015", GameType.FAMILY));
//        games.add(new Game(1L,"Grzybobranie",10, "2009", GameType.FOR_KIDS));
//        games.add(new Game(3L, "PartyTime", 15, "2012", GameType.PARTY));
//        games.add(new Game(4L,"I know", 20, "2012", GameType.FAMILY));
//        games.add(new Game(5L,"Na skrzydłach", 20, "2019", GameType.STRATEGIC));
//        games.add(new Game(6L,"Pojedynek", 15, "2019", GameType.STRATEGIC));
//        games.add(new Game(7L,"Monopol", 10, "2019", GameType.FAMILY));
//        return games;
//    }

    public Set<Game> findByTitle(String title) {
        return games.stream().filter(game -> game.getTitle().contains(title))
                .collect(Collectors.toSet());
    }


    public void save(Game game) {
        this.games.add(game);
        restTemplate.postForObject("http://localhost:8080/v1/games",game,Game.class);
    }
    public void edit(Game game){
        restTemplate.put("http://localhost:8080/v1/games",game,Game.class);
    }


    public void delete(Game game) {
        this.games.remove(game);
        restTemplate.delete("http://localhost:8080/v1/games/"+game.getGameId());
    }
}