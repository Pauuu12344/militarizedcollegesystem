package edu.mx.utleon.militarizedcollegesystem.microservices.academics;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.GradeDto;
import edu.mx.utleon.militarizedcollegesystem.microservices.academics.academics.GradeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GradeTest {

    @Autowired
    private GradeService gradeService;

    @Test
    void getGradesByGroupId() {
        List<GradeDto> grades = gradeService.getGradesByGroupId(1L);
        grades.forEach(System.out::println);
    }

}
