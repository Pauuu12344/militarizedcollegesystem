package edu.mx.utleon.militarizedcollegesystem.microservices.admissions.service;

import edu.mx.utleon.militarizedcollegesystem.microservices.admissions.repository.ApplicantRepository;
import edu.mx.utleon.militarizedcollegesystem.model.admissions.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;

    public Applicant getApplicantById(Long id) {
        Applicant applicant = applicantRepository.findById(id).orElse(null);
        return applicant;
    }

    public List<Applicant> getAllApplicants() {
        return (List<Applicant>) applicantRepository.findAll();
    }

}
