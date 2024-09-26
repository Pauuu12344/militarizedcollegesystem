package edu.mx.utleon.militarizedcollegesystem.microservices.staff.service;

import edu.mx.utleon.militarizedcollegesystem.microservices.staff.repository.PersonRepository;
import edu.mx.utleon.militarizedcollegesystem.model.staff.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person getPersonById(Long id){
        return personRepository.findById(id).orElse(null);
    }

    public List<Person> getAllPersons(){
        return (List<Person>) personRepository.findAll();
    }

    public Person savePerson(Person person){
        return personRepository.save(person);
    }

}
