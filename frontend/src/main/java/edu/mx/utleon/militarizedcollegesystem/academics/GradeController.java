package edu.mx.utleon.militarizedcollegesystem.academics;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.GradeDto;
import edu.mx.utleon.militarizedcollegesystem.common.dtos.UserDto;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Roles;
import edu.mx.utleon.militarizedcollegesystem.users.UserDetailsImpl;
import edu.mx.utleon.militarizedcollegesystem.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GradeController {

    @Autowired
    private PeriodService periodService;

    @Autowired
    private CareerService careerService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private UserService userService;

    @GetMapping("/grades")
    public String viewGrades(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam(required = false) Long periodId,
            @RequestParam(required = false) Long careerId,
            @RequestParam(required = false) Long groupId,
            Model model
    ) {
        UserDto user = userService.getByUsername(userDetails.getUsername());

        if (user.getRole().equals(Roles.ESTUDIANTE.name())) {
            model.addAttribute("studentGrades", gradeService.getGradesByStudentPersonId(user.getPersonId()));
        }

        if (periodId != null && careerId != null) {
            model.addAttribute("selectedPeriod", periodService.getPeriodById(periodId));
            model.addAttribute("selectedCareer", careerService.getCareerById(careerId));
            model.addAttribute("groups", groupService.getAllGroupsByPeriodIdAndCareerId(periodId, careerId));
        }

        if (groupId != null) {
            model.addAttribute("selectedGroup", groupService.getGroupById(groupId));
            model.addAttribute("groupGrades", gradeService.getAllGradesByGroupId(groupId));
        }

        model.addAttribute("periods", periodService.getAllPeriods());
        model.addAttribute("careers", careerService.getAllCareers());
        return "academics/grades";
    }

    @PutMapping("/grades")
    public String updateGrade(@RequestBody GradeDto gradeDto, Model model) {
        gradeService.updateGrade(gradeDto);
        return viewGrades(null, gradeDto.getPeriodId(), gradeDto.getCareerId(), gradeDto.getGroupId(), model);
    }

}
