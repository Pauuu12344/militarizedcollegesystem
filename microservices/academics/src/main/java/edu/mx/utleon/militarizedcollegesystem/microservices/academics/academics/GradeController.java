package edu.mx.utleon.militarizedcollegesystem.microservices.academics.academics;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.GradeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping("/grades")
    public List<GradeDto> getGradesByStudentId(@RequestParam(required = false) Long personId, @RequestParam(required = false) Long groupId) {
        if (personId != null) {
            return gradeService.getGradesByStudentPersonId(personId);
        } else if (groupId != null) {
            return gradeService.getGradesByGroupId(groupId);
        }
        return gradeService.getAllGrades();
    }

    @PutMapping("/grades")
    public void updateGrade(@RequestBody GradeDto gradeDto) {
        gradeService.updateGrade(gradeDto);
    }

}
