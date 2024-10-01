package edu.mx.utleon.militarizedcollegesystem.academics;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.GradeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${microservices.url.academics}")
    private String ACADEMICS_URL;

    public List<GradeDto> getAllGradesByGroupId(Long groupId) {
        String path = ACADEMICS_URL + "grades?groupId=" + groupId;
        return restTemplate.getForObject(path, List.class);

    }

    public void updateGrade(GradeDto gradeDto) {
        String path = ACADEMICS_URL + "grades";
        restTemplate.put(path, gradeDto);
    }

    public List<GradeDto> getGradesByStudentPersonId(Long personId) {
        String path = ACADEMICS_URL + "grades?personId=" + personId;
        return restTemplate.getForObject(path, List.class);
    }
}