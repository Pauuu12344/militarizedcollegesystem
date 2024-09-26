package edu.mx.utleon.militarizedcollegesystem.microservices.admissions.controller;

import edu.mx.utleon.militarizedcollegesystem.microservices.admissions.service.ApplicantService;
import edu.mx.utleon.militarizedcollegesystem.model.admissions.Applicant;
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
