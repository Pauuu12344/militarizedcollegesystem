package edu.mx.utleon.militarizedcollegesystem.microservices.staff.repository;

import edu.mx.utleon.militarizedcollegesystem.model.staff.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}
