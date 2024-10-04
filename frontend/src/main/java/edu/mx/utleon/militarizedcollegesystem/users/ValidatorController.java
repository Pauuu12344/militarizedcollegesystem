package edu.mx.utleon.militarizedcollegesystem.users;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.ValidationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidatorController {

    @Autowired
    private PersonService personService;

    @Autowired
    private UserService userService;

    @Autowired
    private ValidatorService validatorService;

    @PostMapping("/validate")
    public boolean validate(@RequestBody ValidationDto validationDto) {
        System.out.println(validationDto);

        Long id = validationDto.getId();
        String curp = validationDto.getCurp();
        String email = validationDto.getEmail();

        if (curp != null) {
            return validatorService.checkCurp(id, curp);
        } else if (email != null) {
            return validatorService.checkEmail(id, email);
        }
        return false;
    }

}
