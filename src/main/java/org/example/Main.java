package org.example;

import model.User;
import org.springframework.web.client.RestTemplate;
import service.UserService;
import service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        UserService userService = new UserServiceImpl(restTemplate);

        userService.getAllUsers();

        User user1 = new User(3L, "James", "Brown", (byte) 30);
        User user2 = new User(3L, "Thomas", "Shelby", (byte) 35);

        System.out.println((userService.addUser(user1) + userService.updateUser(user2) +
                userService.deleteUser(3L)).length());

    }

}