package edu.mx.utleon.militarizedcollegesystem.microservices.admissions.users;

import edu.mx.utleon.militarizedcollegesystem.common.entities.users.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
