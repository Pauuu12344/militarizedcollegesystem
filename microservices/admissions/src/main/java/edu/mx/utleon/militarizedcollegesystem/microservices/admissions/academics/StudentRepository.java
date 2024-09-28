package edu.mx.utleon.militarizedcollegesystem.microservices.admissions.academics;

import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
