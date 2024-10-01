package edu.mx.utleon.militarizedcollegesystem.academics;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PeriodController {
        @Autowired
        private PeriodService periodService;

        @GetMapping("/periods")
        public String viewApplicant(Model model) {
            model.addAttribute("periods", periodService.getAllPeriods());
            return "admissions/periods";
        }
}
