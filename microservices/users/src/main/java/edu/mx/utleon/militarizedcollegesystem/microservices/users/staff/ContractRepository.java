package edu.mx.utleon.militarizedcollegesystem.microservices.users.staff;

import edu.mx.utleon.militarizedcollegesystem.common.entity.staff.Contract;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractRepository extends CrudRepository<Contract, Long> {

    Optional<Contract> findByType(String type);

}
