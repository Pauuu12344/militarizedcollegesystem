package edu.mx.utleon.militarizedcollegesystem.microservices.users.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
   private PersonRepository personRepository;
    public boolean checkCurp(String curp){
        return personRepository.findByCurp(curp).isPresent();
    }

}
