package edu.mx.utleon.militarizedcollegesystem.users;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

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
        model.addAttribute("usuarios", "Esta es una lista de usuarios");
        return "users/users";
    }

}
