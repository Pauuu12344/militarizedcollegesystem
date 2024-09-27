package edu.mx.utleon.militarizedcollegesystem.microservices.staff.staff;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.PersonnelPersonDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonnelController {
    @Autowired
    private PersonnelService personnelService;
    @GetMapping("/personnel")
    public List<PersonnelPersonDto> getAllPersonnel() {
        return personnelService.getAllPersonnel();
    }
}
