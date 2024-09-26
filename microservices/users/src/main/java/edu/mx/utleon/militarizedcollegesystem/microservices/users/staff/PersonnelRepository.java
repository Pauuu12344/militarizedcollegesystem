package edu.mx.utleon.militarizedcollegesystem.microservices.users.staff;

import edu.mx.utleon.militarizedcollegesystem.common.entity.staff.Personnel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PersonnelRepository extends CrudRepository<Personnel, Long> {

    Optional<Personnel> findByNumber(String number);

}
