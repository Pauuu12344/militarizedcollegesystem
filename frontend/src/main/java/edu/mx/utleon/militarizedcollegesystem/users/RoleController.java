package edu.mx.utleon.militarizedcollegesystem.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public String viewRoles(Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        return "roles/roles";
    }

}
