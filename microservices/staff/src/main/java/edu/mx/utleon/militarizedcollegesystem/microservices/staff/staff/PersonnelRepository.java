package edu.mx.utleon.militarizedcollegesystem.microservices.staff.staff;

import edu.mx.utleon.militarizedcollegesystem.common.entities.staff.Personnel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PersonnelRepository extends CrudRepository<Personnel, Long> {

    Optional<Personnel> findByNumber(String number);

    Optional<Personnel> findByPersonId(Long personId);

}
