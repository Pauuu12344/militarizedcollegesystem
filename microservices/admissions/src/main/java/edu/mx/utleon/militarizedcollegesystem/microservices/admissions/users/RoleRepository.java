package edu.mx.utleon.militarizedcollegesystem.microservices.admissions.users;

import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
