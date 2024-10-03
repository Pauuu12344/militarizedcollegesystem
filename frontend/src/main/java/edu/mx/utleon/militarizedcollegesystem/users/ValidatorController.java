package edu.mx.utleon.militarizedcollegesystem.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidatorController {

    @Autowired
    private PersonService personService;

    @Autowired
    private UserService userService;

    @PostMapping("/validate")
    public boolean validate(@Param("curp") String curp, @Param("email") String email) {
        System.out.println("Validating curp: " + curp + " email: " + email);
        if (curp != null) {
            return personService.checkCurp(curp);
        } else if (email != null) {
            return userService.checkEmail(email);
        }
        return false;
    }

}
