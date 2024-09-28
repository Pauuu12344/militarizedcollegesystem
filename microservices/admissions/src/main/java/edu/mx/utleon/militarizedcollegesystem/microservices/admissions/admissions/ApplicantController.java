package edu.mx.utleon.militarizedcollegesystem.microservices.admissions.admissions;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.ApplicantDto;
import edu.mx.utleon.militarizedcollegesystem.common.entities.admissions.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    @GetMapping("/applicants")
    public List<ApplicantDto> getAllApplicants(@RequestParam(required = false) Long periodId) {
        if (periodId != null) {
            return applicantService.getAllPeriodApplicants(periodId);
        }
        return applicantService.getAllApplicants();
    }

    @PostMapping("/applicants")
    public ApplicantDto createApplicant(@RequestBody ApplicantDto applicantDto) {
        System.out.println(applicantDto);
        return applicantService.createApplicant(applicantDto);
    }

    @PutMapping("/applicants")
    public void updateApplicantStatus(@RequestParam Long id, @RequestParam String status) {
        applicantService.updateApplicantStatus(id, status);
    }

}
