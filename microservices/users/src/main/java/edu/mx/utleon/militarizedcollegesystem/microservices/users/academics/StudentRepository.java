package edu.mx.utleon.militarizedcollegesystem.microservices.users.academics;

import edu.mx.utleon.militarizedcollegesystem.common.entity.academics.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    Optional<Student> findByEnrollment(String enrollment);
}
