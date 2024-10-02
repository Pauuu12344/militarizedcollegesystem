package edu.mx.utleon.militarizedcollegesystem.microservices.scholarships.academics;

import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Grade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends CrudRepository<Grade, Long> {

    Iterable<Grade> findAllByStudentId(Long studentId);

}
