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
