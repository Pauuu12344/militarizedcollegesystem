package edu.mx.utleon.militarizedcollegesystem.microservices.users.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidatorController {

    @Autowired
    private ValidatorService validatorService;

    @PostMapping("/validate")
    public boolean validate(@Param("id") Long id, @Param("curp") String curp, @Param("email") String email) {
        if (curp != null) {
            return validatorService.checkCurp(id, curp);
        } else if (email != null) {
            return validatorService.checkEmail(id, email);
        }
        return false;
    }

}
