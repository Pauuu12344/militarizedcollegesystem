package edu.mx.utleon.militarizedcollegesystem.users;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.UserPersonDto;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/account")
    public String viewAccount(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        model.addAttribute(
                "user",
                userService.getByUsername(userDetails.getUsername())
        );
        return "users/account-form";
    }

    @PostMapping("/account")
    public String updateAccount(UserPersonDto user) {
        userService.updateAccount(user);
        return "redirect:/account";
    }
}
