package edu.mx.utleon.militarizedcollegesystem.microservices.staff.controller;

import edu.mx.utleon.militarizedcollegesystem.microservices.staff.service.PersonService;
import edu.mx.utleon.militarizedcollegesystem.model.staff.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/persons/{id}")
    public Person getPersonById(@PathVariable Long id){
        return personService.getPersonById(id);
    }

    @GetMapping("/persons")
    public List<Person> getAllPersons(){
        return personService.getAllPersons();
    }

    @PostMapping("/persons")
    public Person savePerson(Person person){
        return personService.savePerson(person);
    }

    @PutMapping("/persons")
    public Person updatePerson(Person person){
        return personService.savePerson(person);
    }

}
