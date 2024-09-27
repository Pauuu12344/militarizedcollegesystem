package edu.mx.utleon.militarizedcollegesystem.microservices.academics.academics;

import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Career;
import edu.mx.utleon.militarizedcollegesystem.common.entities.staff.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CareerController {

    @Autowired
    private CareerService careerService;


    @GetMapping("/careers")
    public List<Career> getAllCareers(){
        return careerService.getAllCareers();
    }

}
