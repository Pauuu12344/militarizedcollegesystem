package edu.mx.utleon.militarizedcollegesystem.microservices.users.staff;

import edu.mx.utleon.militarizedcollegesystem.common.entity.staff.Area;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AreaRepository extends CrudRepository<Area, Long> {
    Optional<Area> findByName(String name);
}
