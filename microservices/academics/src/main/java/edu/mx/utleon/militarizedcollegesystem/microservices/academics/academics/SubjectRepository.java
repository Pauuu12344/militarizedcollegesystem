package edu.mx.utleon.militarizedcollegesystem.microservices.academics.academics;

import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {

    @Query("SELECT s FROM Subject s JOIN s.careers c WHERE c.id = :careerId")
    Iterable<Subject> findAllByCareerId(@Param("careerId") Long careerId);

    Optional<Subject> findByName(String name);

}
