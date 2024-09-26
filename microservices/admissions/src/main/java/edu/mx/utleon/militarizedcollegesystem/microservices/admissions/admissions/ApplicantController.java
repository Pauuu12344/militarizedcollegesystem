package edu.mx.utleon.militarizedcollegesystem.microservices.admissions.admissions;

import edu.mx.utleon.militarizedcollegesystem.common.entities.admissions.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    @GetMapping("/applicants/{id}")
    public Applicant getApplicantById(@PathVariable Long id) {
        return applicantService.getApplicantById(id);
    }

}
