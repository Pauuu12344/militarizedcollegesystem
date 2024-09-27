package edu.mx.utleon.militarizedcollegesystem.microservices.users.users;

import edu.mx.utleon.militarizedcollegesystem.common.entities.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public User getUser(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String username
    ) {
        if(id != null) {
            return userService.getUserById(id);
        } else if(username != null) {
            return userService.getUserByUsername(username);
        }
        return null;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
