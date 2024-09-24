package edu.mx.utleon.militarizedcollegesystem.microservices.users.controllers;

import edu.mx.utleon.militarizedcollegesystem.microservices.users.services.UserService;
import edu.mx.utleon.militarizedcollegesystem.model.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{username}")
    public User getByUsername(@PathVariable String username) {
        return userService.getByUsername(username);
    }
}
