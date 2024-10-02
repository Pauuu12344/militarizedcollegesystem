package edu.mx.utleon.militarizedcollegesystem.scholarships;

import edu.mx.utleon.militarizedcollegesystem.academics.StudentService;
import edu.mx.utleon.militarizedcollegesystem.common.dtos.ApplicationDto;
import edu.mx.utleon.militarizedcollegesystem.common.dtos.StudentDto;
import edu.mx.utleon.militarizedcollegesystem.common.dtos.UserDto;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Roles;
import edu.mx.utleon.militarizedcollegesystem.users.UserDetailsImpl;
import edu.mx.utleon.militarizedcollegesystem.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ScholarshipController {

    @Autowired
    private ScholarshipService scholarshipService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/scholarships")
    public String scholarships(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        UserDto user = userService.getByUsername(userDetails.getUsername());

        if (user.getRole().equals(Roles.ESTUDIANTE.name())) {
            StudentDto student = studentService.getStudentByPersonId(user.getPersonId());
            model.addAttribute("studentApplication", ApplicationDto.builder().studentId(student.getStudentId()).build());
            model.addAttribute("studentApplications", applicationService.getApplicationsByStudentId(student.getStudentId()));
        }

        if(user.getRole().equals(Roles.SERVICIOS_ESCOLARES.name())) {
            model.addAttribute("allApplications", applicationService.getAllApplications());
        }
        model.addAttribute("scholarships", scholarshipService.getAllScholarships());
        return "scholarships/scholarships";
    }

    @PostMapping("/scholarships/apply")
    public String applyForScholarship(ApplicationDto applicationDto) {
        applicationService.applyForScholarship(applicationDto);
        return "redirect:/scholarships";
    }

    @GetMapping("/scholarships/accept/{id}")
    public String acceptApplication(@PathVariable Long id) {
        applicationService.changeApplicationStatus(id, "ACEPTADA");
        return "redirect:/scholarships";
    }

    @GetMapping("/scholarships/reject/{id}")
    public String rejectApplication(@PathVariable Long id) {
        applicationService.changeApplicationStatus(id, "RECHAZADA");
        return "redirect:/scholarships";
    }

}
