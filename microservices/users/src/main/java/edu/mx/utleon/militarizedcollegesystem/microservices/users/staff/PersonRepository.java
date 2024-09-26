package edu.mx.utleon.militarizedcollegesystem.microservices.users.staff;

import edu.mx.utleon.militarizedcollegesystem.common.entity.staff.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    Optional<Person> findByCurp(String curp);

}
