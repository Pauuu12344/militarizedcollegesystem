package edu.mx.utleon.militarizedcollegesystem.microservices.scholarships.users;

import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}
