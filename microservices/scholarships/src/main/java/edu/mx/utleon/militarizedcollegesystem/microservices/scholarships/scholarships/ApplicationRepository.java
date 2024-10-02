package edu.mx.utleon.militarizedcollegesystem.microservices.scholarships.scholarships;

import edu.mx.utleon.militarizedcollegesystem.common.entities.scholarships.Application;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ApplicationRepository extends CrudRepository<Application, Long> {
    Iterable<Application> findAllByStudentId(Long studentId);
}
