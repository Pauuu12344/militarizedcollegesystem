package edu.mx.utleon.militarizedcollegesystem.microservices.users.controllers;

import edu.mx.utleon.militarizedcollegesystem.microservices.users.services.UserService;
import edu.mx.utleon.militarizedcollegesystem.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public User saveUser(User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/users")
    public User updateUser(User user) {
        return userService.saveUser(user);
    }

}
