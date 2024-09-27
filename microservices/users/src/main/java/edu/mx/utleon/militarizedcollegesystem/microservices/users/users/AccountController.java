package edu.mx.utleon.militarizedcollegesystem.microservices.users.users;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.UserPersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private UserService userService;


    @PostMapping("/account")
    public UserPersonDto saveUser(@RequestBody UserPersonDto user) {
        System.out.println(user);
        return userService.updateAccount(user);
    }

}
