package edu.mx.utleon.militarizedcollegesystem.microservices.staff.controller;

import edu.mx.utleon.militarizedcollegesystem.microservices.staff.service.AreaService;
import edu.mx.utleon.militarizedcollegesystem.common.entity.staff.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AreaController {
    @Autowired
    private AreaService areaService;

    @GetMapping("/areas/{id}")
    public Area getAreaById(@PathVariable Long id){
        return areaService.getAreaById(id);

    }
    @GetMapping("/areas")
    public List<Area> getAllAreas(){
        return areaService.getAllAreas();

    }

}
