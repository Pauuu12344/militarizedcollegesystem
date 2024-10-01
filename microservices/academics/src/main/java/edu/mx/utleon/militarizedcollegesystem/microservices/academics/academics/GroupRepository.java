package edu.mx.utleon.militarizedcollegesystem.microservices.academics.academics;

import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Group;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {

    @Query("SELECT g FROM Group g JOIN g.students s JOIN s.career c JOIN s.period p WHERE p.id = :periodId AND c.id = :careerId")
    Iterable<Group> findGroupsByPeriodIdAndCareerId(Long periodId, Long careerId);

}
