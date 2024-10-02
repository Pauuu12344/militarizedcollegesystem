package edu.mx.utleon.militarizedcollegesystem.microservices.scholarships.scholarships;

import edu.mx.utleon.militarizedcollegesystem.common.entities.scholarships.Scholarship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScholarshipService {

    @Autowired
    private ScholarshipRepository scholarshipRepository;

    public List<Scholarship> getAllScholarships() {
        return ((List<Scholarship>) scholarshipRepository.findAll());
    }

}
