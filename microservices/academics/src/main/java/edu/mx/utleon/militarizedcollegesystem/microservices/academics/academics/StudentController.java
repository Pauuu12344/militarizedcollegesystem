package edu.mx.utleon.militarizedcollegesystem.microservices.academics.academics;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public List<StudentDto> getAllStudents(@RequestParam(required = false) Long periodId, @RequestParam(required = false) Long careerId) {
        if (periodId != null && careerId != null) {
            return studentService.getAllStudentsByPeriodIdAndCareerId(periodId, careerId);
        }
        return studentService.getAllStudents();
    }

}
