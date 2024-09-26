package edu.mx.utleon.militarizedcollegesystem.microservices.admissions.admissions;

import edu.mx.utleon.militarizedcollegesystem.common.entities.admissions.Applicant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepository extends CrudRepository<Applicant, Long> {

}
