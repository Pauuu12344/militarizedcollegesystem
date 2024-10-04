package edu.mx.utleon.militarizedcollegesystem.microservices.users.users;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.UserDto;
import edu.mx.utleon.militarizedcollegesystem.common.entities.admissions.Applicant;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Person;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.User;
import edu.mx.utleon.militarizedcollegesystem.microservices.users.admissions.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidatorService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ApplicantRepository applicantRepository;

    public boolean checkEmail(Long id, String email) {
        if (id != null) {
            User user = userRepository.findByPersonId(id).orElse(null);
            if (user != null && user.getEmail().equals(email)) return false;
        }
        return userRepository.findByEmail(email).isPresent() || applicantRepository.findByEmail(email).isPresent();
    }

    public boolean checkCurp(Long id, String curp) {
        if (id != null) {
            Person person = personRepository.findById(id).orElse(null);
            if (person != null && person.getCurp().equals(curp)) return false;
        }
        return personRepository.findByCurp(curp).isPresent();
    }


}
