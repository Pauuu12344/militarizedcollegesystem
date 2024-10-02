package edu.mx.utleon.militarizedcollegesystem.microservices.scholarships.scholarships;

import edu.mx.utleon.militarizedcollegesystem.common.entities.scholarships.Scholarship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScholarshipController {

    @Autowired
    private ScholarshipService scholarshipService;

    @GetMapping("/scholarships")
    public List<Scholarship> getAllScholarships() {
        return scholarshipService.getAllScholarships();
    }

}
