package edu.mx.utleon.militarizedcollegesystem.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidationsController {

    @Autowired
    private PersonService personService;

    @GetMapping("/validations")
    public boolean checkCurp(@RequestParam String curp){
        return personService.checkCurp(curp);
    }

}
