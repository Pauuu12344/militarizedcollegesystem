package edu.mx.utleon.militarizedcollegesystem.microservices.academics.academics;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/subjects")
    public List<SubjectDto> getAllSubjects(@RequestParam(required = false) Long careerId) {
        if (careerId != null) {
            return subjectService.getAllSubjectsByCareerId(careerId);
        }
        return subjectService.getAllSubjects();
    }

}
