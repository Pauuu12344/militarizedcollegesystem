package edu.mx.utleon.militarizedcollegesystem.admissions;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.ApplicantDto;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ApplicantController {

    @Autowired
    private CareerService careerService;

    @Autowired
    private ApplicantService applicantService;

    @GetMapping("/applicant")
    public String viewApplicant(Model model) {
        return "admissions/applicant";
    }

    @GetMapping("/applicant/apply")
    public String viewApply(Model model) {
        model.addAttribute("applicant", new ApplicantDto());
        model.addAttribute("careers", careerService.getAllCareers());
        return "admissions/applicant-form";
    }

    @PostMapping("/applicant/apply")
    public String apply(ApplicantDto applicantDto) {
        applicantService.createApplicant(applicantDto);
        return "redirect:/applicant/apply";
    }

}
