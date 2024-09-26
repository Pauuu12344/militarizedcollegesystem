package edu.mx.utleon.militarizedcollegesystem.microservices.staff.repository;

import edu.mx.utleon.militarizedcollegesystem.common.entity.staff.Area;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends CrudRepository<Area,Long> {
}
