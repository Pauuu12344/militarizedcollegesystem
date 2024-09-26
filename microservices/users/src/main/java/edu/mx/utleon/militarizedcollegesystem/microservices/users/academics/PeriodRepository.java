package edu.mx.utleon.militarizedcollegesystem.microservices.users.academics;

import edu.mx.utleon.militarizedcollegesystem.common.entity.academics.Period;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeriodRepository extends CrudRepository<Period, Long> {
    Optional<Period> findByName(String name);
}
