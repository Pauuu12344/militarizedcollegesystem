package edu.mx.utleon.militarizedcollegesystem.microservices.academics.users;

import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
