package com.project.boardgamerentalfront.service;

import com.project.boardgamerentalfront.domain.Game;
import com.project.boardgamerentalfront.domain.Rent;
import com.project.boardgamerentalfront.domain.User;
import com.project.boardgamerentalfront.enums.GameType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RentService {
    private final RestTemplate restTemplate = new RestTemplate();
    private Set<Rent> rents;
    private static RentService rentService;


    private RentService() {
        this.rents = getRents();
    }

    public static RentService getInstance() {
        if (rentService == null) {
            rentService = new RentService();
        }
        return rentService;
    }

    public Set<Rent> getRents() {
        ResponseEntity<Rent[]> rs = restTemplate.getForEntity("http://localhost:8080/v1/rents",Rent[].class);
        return Arrays.stream(rs.getBody()).collect(Collectors.toSet());
    }


    private Set<Rent> exampleData() {
        Set<Rent> rents = new HashSet<>();
        rents.add(new Rent(1l,new User(1L,"Hania","Bania","bania@hania","465613"),new Game(1L,"Grzybobranie",10, "2009", GameType.FOR_KIDS),LocalDate.now(),LocalDate.now().plusDays(7L)));

        return rents;
    }

    public Set<Rent> findByPhoneNumber(String number) {
        return rents.stream().filter(rent -> rent.getUser().getPhoneNumber().contains(number))
                .collect(Collectors.toSet());
    }


    public void save(Rent rent) {
        this.rents.add(rent);
        restTemplate.postForObject("http://localhost:8080/v1/rents", rent, Rent.class);
    }

    public void edit(Rent rent) {
        restTemplate.put("http://localhost:8080/v1/rents",rent,Rent.class);
    }

    public void delete(Rent rent) {
        this.rents.remove(rent);
        restTemplate.delete("http://localhost:8080/v1/rents/"+rent.getRentId());
    }
}
