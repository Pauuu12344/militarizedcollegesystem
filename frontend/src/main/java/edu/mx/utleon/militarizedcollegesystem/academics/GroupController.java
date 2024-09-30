package edu.mx.utleon.militarizedcollegesystem.academics;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.GroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private PeriodService periodService;

    @Autowired
    private CareerService careerService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/groups")
    public String viewGroups(Model model) {
        model.addAttribute("groups", groupService.getAllGroups());
        return "academics/groups";
    }

    @GetMapping("/groups/new")
    public String viewNewGroup(
            @RequestParam(required = false) Long periodId,
            @RequestParam(required = false) Long careerId,
            Model model
    ) {
        if (periodId != null && careerId != null) {
            model.addAttribute("selectedPeriod", periodService.getPeriodById(periodId));
            model.addAttribute("selectedCareer", careerService.getCareerById(careerId));
            model.addAttribute("students", studentService.getAllStudentsByPeriodIdAndCareerId(periodId, careerId));
            model.addAttribute("subjects", subjectService.getAllSubjectsByCareerId(careerId));
            model.addAttribute("group", new GroupDto());
        }

        model.addAttribute("periods", periodService.getAllPeriods());
        model.addAttribute("careers", careerService.getAllCareers());
        return "academics/group-form";
    }

}
