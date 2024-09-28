package edu.mx.utleon.militarizedcollegesystem.microservices.academics.academics;

import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeriodController {

    @Autowired
    private PeriodService periodService;

    @GetMapping("/period")
    public Period getPeriod(@RequestParam(required = false) Long id) {
       if (id != null) {
           return periodService.getPeriodById(id);
       } else {
           return periodService.getCurrentPeriod();
       }
    }

}
