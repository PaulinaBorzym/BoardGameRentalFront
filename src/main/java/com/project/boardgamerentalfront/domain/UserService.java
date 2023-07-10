package com.project.boardgamerentalfront.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserService {
    private Set<User> users;
    private static UserService userService;

    private UserService() {
        this.users = exampleData();
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public Set<User> getUsers() {
        return new HashSet<>(users);
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    private Set<User> exampleData() {
        Set<User> users = new HashSet<>();
        users.add(new User("Ania","Kania","kania@ania","55555"));
        users.add(new User("Hania","Bania","bania@hania","465613"));
        users.add(new User("Artur","Kot","artur@kot","4613"));
        users.add(new User("Jacek","Placek","placek@jacek","74513"));
        return users;
    }

    public Set<User> findByPhoneNumber(String phoneNumber) {
        return users.stream().filter(user -> user.getPhoneNumber().contains(phoneNumber))
                .collect(Collectors.toSet());
    }

    public void save(User user) {
        this.users.add(user);
    }

    public void delete(User user) {
        this.users.remove(user);
    }
}
