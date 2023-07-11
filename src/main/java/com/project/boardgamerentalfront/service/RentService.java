package com.project.boardgamerentalfront.service;

import com.project.boardgamerentalfront.domain.Game;
import com.project.boardgamerentalfront.domain.Rent;
import com.project.boardgamerentalfront.domain.User;
import com.project.boardgamerentalfront.enums.GameType;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RentService {

    private Set<Rent> rents;
    private static RentService rentService;
    private static UserService userService;
    private static GameService gameService;


    private RentService() {
        this.rents = exampleData();
    }

    public static RentService getInstance() {
        if (rentService == null) {
            rentService = new RentService();
        }
        return rentService;
    }

    public Set<Rent> getRents() {
        return new HashSet<>(rents);
    }

    public void addRents(Rent rent) {
        this.rents.add(rent);
    }

    private Set<Rent> exampleData() {
        Set<Rent> rents = new HashSet<>();
        rents.add(new Rent(new User("Hania","Bania","bania@hania","465613"),new Game("Grzybobranie",10, "2009", GameType.FOR_KIDS),LocalDate.now(),LocalDate.now().plusDays(7L)));

        return rents;
    }

    public Set<Rent> findByPhoneNumber(String number) {
        return rents.stream().filter(rent -> rent.getUser().getPhoneNumber().contains(number))
                .collect(Collectors.toSet());
    }


    public void save(Rent rent) {
        this.rents.add(rent);
    }

    public void delete(Rent rent) {
        this.rents.remove(rent);
    }
}
