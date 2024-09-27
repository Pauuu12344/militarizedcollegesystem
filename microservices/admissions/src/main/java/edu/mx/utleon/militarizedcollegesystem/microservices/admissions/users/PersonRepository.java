package edu.mx.utleon.militarizedcollegesystem.microservices.admissions.users;

import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}