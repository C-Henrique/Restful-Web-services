package com.chenrique.rest.webservices.restful_web_services.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDAOService {

    private static List<User> users = new ArrayList<>();
    private static int usersCount = 0;
    static {
        users.add(new User(++usersCount, "Henrique", LocalDate.now().minusYears(1)));
        users.add(new User(++usersCount, "Silva", LocalDate.now().minusYears(2)));
        users.add(new User(++usersCount, "Oliveira", LocalDate.now().minusYears(3)));

    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(Integer id) {
        return users.stream().filter(u -> u.getId().equals(id)).findFirst().get();
    }

    public User save(User user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }
}
