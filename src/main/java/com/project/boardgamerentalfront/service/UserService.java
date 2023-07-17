package com.project.boardgamerentalfront.service;

import com.project.boardgamerentalfront.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class UserService {
    private final RestTemplate restTemplate = new RestTemplate();
    private Set<User> users;
    private static UserService userService;

    private UserService() {
        this.users = getUsers();
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public Set<User> getUsers() {
        ResponseEntity<User[]> rs = restTemplate.getForEntity("http://localhost:8080/v1/users", User[].class);
        return Arrays.stream(rs.getBody()).collect(Collectors.toSet());
    }

    public Set<User> findByPhoneNumber(String phoneNumber) {
        return users.stream().filter(user -> user.getPhoneNumber().contains(phoneNumber))
                .collect(Collectors.toSet());
    }

    public void save(User user) {
        this.users.add(user);
        restTemplate.postForObject("http://localhost:8080/v1/users", user, User.class);
    }

    public void edit(User user) {
        restTemplate.put("http://localhost:8080/v1/users", user, User.class);
    }

    public void delete(User user) {
        this.users.remove(user);
        restTemplate.delete("http://localhost:8080/v1/users/" + user.getUserId());
    }
}