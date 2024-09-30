package edu.mx.utleon.militarizedcollegesystem.academics;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${microservices.url.academics}")
    private String ACADEMICS_URL;

    public List<StudentDto> getAllStudentsByPeriodIdAndCareerId(Long periodId, Long careerId) {
        String path = ACADEMICS_URL + "students?periodId=" + periodId + "&careerId=" + careerId;
        return restTemplate.getForObject(path, List.class);
    }

}
