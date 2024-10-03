package edu.mx.utleon.militarizedcollegesystem.microservices.users.admissions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;

    public boolean checkEmail(String email) {
        return applicantRepository.findByEmail(email).isPresent();
    }

}
