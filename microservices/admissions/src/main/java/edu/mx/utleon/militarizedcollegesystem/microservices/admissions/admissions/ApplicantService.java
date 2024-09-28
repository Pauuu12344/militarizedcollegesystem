package edu.mx.utleon.militarizedcollegesystem.microservices.admissions.admissions;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.ApplicantDto;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Career;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Period;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Student;
import edu.mx.utleon.militarizedcollegesystem.common.entities.admissions.Applicant;
import edu.mx.utleon.militarizedcollegesystem.common.entities.admissions.Status;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Person;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Role;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Roles;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.User;
import edu.mx.utleon.militarizedcollegesystem.microservices.admissions.academics.CareerRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.admissions.academics.PeriodRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.admissions.academics.StudentRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.admissions.users.PersonRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.admissions.users.RoleRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.admissions.users.UserRepository;
import jakarta.transaction.Transactional;
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
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;

    public List<ApplicantDto> getAllApplicants() {
        return ((List<Applicant>) applicantRepository.findAll()).stream()
                .map(this::buildApplicantDto)
                .collect(java.util.stream.Collectors.toList());
    }

    @Transactional
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
                        .email(applicantDto.getEmail())
                        .personId(person.getId())
                        .careerId(career.getId())
                        .periodId(period.getId())
                        .status(Status.PENDIENTE.name())
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
                .email(applicant.getEmail())
                .personId(person.getId())
                .fullName(person.getFullName())
                .phone(person.getPhone())
                .curp(person.getCurp())
                .careerId(career.getId())
                .career(career.getName())
                .periodId(period.getId())
                .period(period.getStartYear())
                .status(applicant.getStatus())
                .build();
    }

    public List<ApplicantDto> getAllPeriodApplicants(Long periodId) {
        return ((List<Applicant>) applicantRepository.findAllByPeriodId(periodId)).stream()
                .map(this::buildApplicantDto)
                .collect(java.util.stream.Collectors.toList());
    }

    @Transactional
    public void updateApplicantStatus(Long id, String status) {
        Applicant applicant = applicantRepository.findById(id).orElse(null);
        if (applicant != null) {
            if (status.equals(Status.ACEPTADO.name())) {
                applicant.setStatus(Status.ACEPTADO.name());
                Person personStudent = personRepository.findById(applicant.getPersonId()).orElse(null);
                Role roleStudent = roleRepository.findByName(Roles.ESTUDIANTE.name()).orElse(null);
                userRepository.save(User.builder()
                        .email(applicant.getEmail())
                        .username(applicant.getEnrollment())
                        .password(applicant.getEnrollment())
                        .role(roleStudent)
                        .active(true)
                        .person(personStudent)
                        .build()
                );
                studentRepository.save(Student.builder()
                        .enrollment(applicant.getEnrollment())
                        .personId(applicant.getPersonId())
                        .build())
                ;
            } else if (status.equals(Status.RECHAZADO.name())) {
                applicant.setStatus(Status.RECHAZADO.name());
            }
            applicantRepository.save(applicant);
        }
    }
}
