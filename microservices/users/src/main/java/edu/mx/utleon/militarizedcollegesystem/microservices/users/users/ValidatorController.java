package edu.mx.utleon.militarizedcollegesystem.microservices.users.users;

import edu.mx.utleon.militarizedcollegesystem.microservices.users.admissions.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidatorController {

    @Autowired
    private PersonService personService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicantService applicantService;

    @PostMapping("/validate")
    public boolean validate(@Param("curp") String curp, @Param("email") String email) {
        if (curp != null) {
            return personService.checkCurp(curp);
        } else if (email != null) {
            return userService.checkEmail(email) || applicantService.checkEmail(email);
        }
        return false;
    }

}
