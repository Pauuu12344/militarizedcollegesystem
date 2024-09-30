package edu.mx.utleon.militarizedcollegesystem.microservices.academics.academics;

import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PeriodService {

    @Autowired
    private PeriodRepository periodRepository;

    public Period getPeriodById(Long id) {
        return periodRepository.findById(id).orElse(null);
    }

    public Period getCurrentPeriod() {
        return periodRepository.findByStartYear(LocalDate.now().getYear()).orElse(null);
    }

    public List<Period> getAllPeriods() {
        return (List<Period>) periodRepository.findAll();
    }
}
