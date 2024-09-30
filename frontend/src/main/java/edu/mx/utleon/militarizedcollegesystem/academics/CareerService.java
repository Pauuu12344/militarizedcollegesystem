package edu.mx.utleon.militarizedcollegesystem.academics;

import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Career;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CareerService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${microservices.url.academics}")
    private String ACADEMICS_URL;

    public List<Career> getAllCareers() {
        String path = ACADEMICS_URL + "careers";
        return restTemplate.getForObject(path, List.class);
    }

    public Career getCareerById(Long careerId) {
        String path = ACADEMICS_URL + "career?id=" + careerId;
        return restTemplate.getForObject(path, Career.class);
    }
}
