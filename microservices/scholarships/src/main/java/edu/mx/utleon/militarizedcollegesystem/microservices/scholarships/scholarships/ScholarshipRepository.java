package edu.mx.utleon.militarizedcollegesystem.microservices.scholarships.scholarships;

import edu.mx.utleon.militarizedcollegesystem.common.entities.scholarships.Scholarship;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScholarshipRepository extends CrudRepository<Scholarship, Long> {
    Optional<Scholarship> findByName(String name);
}
