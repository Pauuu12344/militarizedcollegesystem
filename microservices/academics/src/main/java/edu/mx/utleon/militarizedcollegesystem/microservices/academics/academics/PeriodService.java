package edu.mx.utleon.militarizedcollegesystem.microservices.academics.academics;

import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeriodService {

    @Autowired
    private PeriodRepository periodRepository;

    public Period getPeriodById(Long id) {
        return periodRepository.findById(id).orElse(null);
    }

    public Period getCurrentPeriod() {
        int year = java.time.LocalDate.now().getYear();
        return periodRepository.findByStartYear(year).orElse(null);
    }

}
