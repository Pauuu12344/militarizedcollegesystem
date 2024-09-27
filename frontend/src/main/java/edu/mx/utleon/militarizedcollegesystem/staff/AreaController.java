package edu.mx.utleon.militarizedcollegesystem.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping("/areas")
    public String viewAreas(Model model) {
        model.addAttribute("areas", areaService.getAllAreas());
        return "staff/areas";
    }

}
