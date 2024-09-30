package edu.mx.utleon.militarizedcollegesystem.microservices.academics.academics;

import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {
}
