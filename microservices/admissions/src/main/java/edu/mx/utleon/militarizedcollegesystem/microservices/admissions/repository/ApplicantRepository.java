package edu.mx.utleon.militarizedcollegesystem.microservices.admissions.repository;

import edu.mx.utleon.militarizedcollegesystem.common.entity.admissions.Applicant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepository extends CrudRepository<Applicant, Long> {

}
