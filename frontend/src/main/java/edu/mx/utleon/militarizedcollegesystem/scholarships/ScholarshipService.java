package edu.mx.utleon.militarizedcollegesystem.scholarships;

import edu.mx.utleon.militarizedcollegesystem.common.entities.scholarships.Scholarship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ScholarshipService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${microservices.url.scholarships}")
    private String SCHOLARSHIPS_URL;

    public List<Scholarship> getAllScholarships() {
        String path = SCHOLARSHIPS_URL + "scholarships";
        return restTemplate.getForObject(path, List.class);
    }

}
