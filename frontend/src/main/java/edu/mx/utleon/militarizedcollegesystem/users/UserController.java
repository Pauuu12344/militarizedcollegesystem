package edu.mx.utleon.militarizedcollegesystem.users;

import edu.mx.utleon.militarizedcollegesystem.common.dto.UserPersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute(
                "users",
                userService.getAllUsers()
        );
        return "users/users";
    }

    @GetMapping("/users/new")
    public String newUser(Model model) {
        model.addAttribute(
                "user",
                new UserPersonDto()
        );

        model.addAttribute(
                "roles",
                roleService.getAllRoles()
        );
        return "users/user-form";
    }

}
