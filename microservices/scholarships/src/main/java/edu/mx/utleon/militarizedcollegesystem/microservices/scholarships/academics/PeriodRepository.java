package edu.mx.utleon.militarizedcollegesystem.microservices.scholarships.academics;

import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Period;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodRepository extends CrudRepository<Period, Long> {
}
