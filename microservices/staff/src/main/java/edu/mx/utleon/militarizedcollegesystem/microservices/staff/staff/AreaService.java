package edu.mx.utleon.militarizedcollegesystem.microservices.staff.staff;

import edu.mx.utleon.militarizedcollegesystem.common.entities.staff.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AreaService {
    @Autowired
 private  AreaRepository areaRepository;
    public Area getAreaById(Long id){
        Area area= areaRepository.findById(id).orElse(null);
        return  area;
    }
    public List<Area> getAllAreas(){
        return(List<Area>) areaRepository.findAll();
    }
}