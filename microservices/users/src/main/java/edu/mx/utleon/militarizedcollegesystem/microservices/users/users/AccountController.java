package edu.mx.utleon.militarizedcollegesystem.microservices.users.users;

import edu.mx.utleon.militarizedcollegesystem.common.entities.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private UserService userService;


    @PostMapping("/account")
    public User saveUser(@RequestBody User user) {
        return userService.updateAccount(user);
    }

}
