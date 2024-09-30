package edu.mx.utleon.militarizedcollegesystem.academics;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${microservices.url.academics}")
    private String ACADEMICS_URL;

    public List<SubjectDto> getAllSubjectsByCareerId(Long careerId) {
        String path = ACADEMICS_URL + "subjects?careerId=" + careerId;
        return restTemplate.getForObject(path, List.class);
    }

}
