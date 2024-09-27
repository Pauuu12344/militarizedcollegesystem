package edu.mx.utleon.militarizedcollegesystem.microservices.admissions.admissions;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.ApplicantDto;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Career;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Period;
import edu.mx.utleon.militarizedcollegesystem.common.entities.admissions.Applicant;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Person;
import edu.mx.utleon.militarizedcollegesystem.microservices.admissions.academics.CareerRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.admissions.academics.PeriodRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.admissions.users.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CareerRepository careerRepository;

    @Autowired
    private PeriodRepository periodRepository;

    public List<ApplicantDto> getAllApplicants() {
        return ((List<Applicant>) applicantRepository.findAll()).stream()
                .map(this::buildApplicantDto)
                .collect(java.util.stream.Collectors.toList());
    }

    public ApplicantDto createApplicant(ApplicantDto applicantDto) {
        Person person = personRepository.save(
                Person.builder()
                .fullName(applicantDto.getFullName())
                .phone(applicantDto.getPhone())
                .curp(applicantDto.getCurp())
                .build()
        );

        Career career = careerRepository.findById(applicantDto.getCareerId()).orElse(null);
        Period period = periodRepository.findById(applicantDto.getPeriodId()).orElse(null);

        return buildApplicantDto(applicantRepository.save(
                Applicant.builder()
                .personId(person.getId())
                .careerId(career.getId())
                .periodId(period.getId())
                .status(false)
                .build()
        ));
    }

    public ApplicantDto buildApplicantDto(Applicant applicant) {
        Person person = personRepository.findById(applicant.getPersonId()).orElse(null);
        Career career = careerRepository.findById(applicant.getCareerId()).orElse(null);
        Period period = periodRepository.findById(applicant.getPeriodId()).orElse(null);
        return ApplicantDto.builder()
                .applicantId(applicant.getId())
                .enrollment(applicant.getEnrollment())
                .personId(person.getId())
                .fullName(person.getFullName())
                .phone(person.getPhone())
                .curp(person.getCurp())
                .careerId(career.getId())
                .career(career.getName())
                .periodId(period.getId())
                .period(period.getName())
                .build();
    }

}
