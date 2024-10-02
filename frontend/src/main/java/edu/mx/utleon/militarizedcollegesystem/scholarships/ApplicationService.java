package edu.mx.utleon.militarizedcollegesystem.scholarships;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.ApplicationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${microservices.url.scholarships}")
    private String SCHOLARSHIPS_URL;

    public List<ApplicationDto> getApplicationsByStudentId(Long studentId) {
        String path = SCHOLARSHIPS_URL + "applications?studentId=" + studentId;
        return restTemplate.exchange(path, HttpMethod.GET, null, new ParameterizedTypeReference<List<ApplicationDto>>() {}).getBody();
    }

    public List<ApplicationDto> getAllApplications() {
        String path = SCHOLARSHIPS_URL + "applications";
        return restTemplate.exchange(path, HttpMethod.GET, null, new ParameterizedTypeReference<List<ApplicationDto>>() {}).getBody();
    }

    public void applyForScholarship(ApplicationDto applicationDto) {
        String path = SCHOLARSHIPS_URL + "applications";
        restTemplate.postForObject(path, applicationDto, ApplicationDto.class);
    }

    public void changeApplicationStatus(Long id, String status) {
        String path = SCHOLARSHIPS_URL + "application?id=" + id + "&status=" + status;
        restTemplate.put(path, null);
    }
}
