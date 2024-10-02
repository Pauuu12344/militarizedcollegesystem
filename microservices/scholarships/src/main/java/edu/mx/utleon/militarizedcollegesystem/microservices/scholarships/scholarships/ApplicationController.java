package edu.mx.utleon.militarizedcollegesystem.microservices.scholarships.scholarships;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.ApplicationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/applications")
    public List<ApplicationDto> getApplicationsByStudentId(@RequestParam(required = false) Long studentId) {
        if (studentId != null) {
            return applicationService.getAllApplicationsByStudentId(studentId);
        }
        return applicationService.getAllApplications();
    }

    @PostMapping("/applications")
    public ApplicationDto applyForScholarship(@RequestBody ApplicationDto applicationDto) {
        return applicationService.applyForScholarship(applicationDto);
    }

    @PutMapping("/application")
    public void changeApplicationStatus(@RequestParam Long id, @RequestParam String status) {
        applicationService.changeApplicationStatus(id, status);
    }

}
