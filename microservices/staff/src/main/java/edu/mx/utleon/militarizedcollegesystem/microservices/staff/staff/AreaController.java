package edu.mx.utleon.militarizedcollegesystem.microservices.staff.staff;

import edu.mx.utleon.militarizedcollegesystem.common.entities.staff.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping("/area")
    public Area getAreaById(@RequestParam(required = false) Long id) {
        if (id != null) {
            return areaService.getAreaById(id);
        }
        return null;
    }

    @GetMapping("/areas")
    public List<Area> getAllAreas() {
        return areaService.getAllAreas();

    }

}
