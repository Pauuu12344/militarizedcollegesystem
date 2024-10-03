package edu.mx.utleon.militarizedcollegesystem.microservices.users.admissions;

import edu.mx.utleon.militarizedcollegesystem.common.entities.admissions.Applicant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicantRepository extends CrudRepository<Applicant, Long> {
    Optional<Applicant> findByEmail(String email);
}
