package edu.mx.utleon.militarizedcollegesystem.microservices.academics.academics;

import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Career;
import edu.mx.utleon.militarizedcollegesystem.common.entities.staff.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CareerService {

    @Autowired
    private  CareerRepository careerRepository;

    public List<Career> getAllCareers(){
        return(List<Career>) careerRepository.findAll();
    }

    public Career getCareerById(Long id) {
        return careerRepository.findById(id).orElse(null);
    }
}
