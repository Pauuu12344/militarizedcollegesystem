package edu.mx.utleon.militarizedcollegesystem.microservices.scholarships.scholarships;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.ApplicantDto;
import edu.mx.utleon.militarizedcollegesystem.common.dtos.ApplicationDto;
import edu.mx.utleon.militarizedcollegesystem.common.dtos.GradeDto;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Career;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Grade;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Period;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Student;
import edu.mx.utleon.militarizedcollegesystem.common.entities.admissions.Applicant;
import edu.mx.utleon.militarizedcollegesystem.common.entities.scholarships.Application;
import edu.mx.utleon.militarizedcollegesystem.common.entities.scholarships.Scholarship;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Person;
import edu.mx.utleon.militarizedcollegesystem.microservices.scholarships.academics.CareerRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.scholarships.academics.GradeRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.scholarships.academics.PeriodRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.scholarships.academics.StudentRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.scholarships.users.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private ScholarshipRepository scholarshipRepository;

    @Autowired
    private CareerRepository careerRepository;

    @Autowired
    private PeriodRepository periodRepository;


    public List<ApplicationDto> getAllApplicationsByStudentId(Long studentId) {
        return ((List<Application>) applicationRepository.findAllByStudentId(studentId))
                .stream()
                .map(this::buildApplicationDto)
                .toList();
    }

    public List<ApplicationDto> getAllApplications() {
        return ((List<Application>) applicationRepository.findAll())
                .stream()
                .map(this::buildApplicationDto)
                .toList();
    }

    @Transactional
    public ApplicationDto applyForScholarship(ApplicationDto applicationDto) {
        Scholarship scholarship = scholarshipRepository.findById(applicationDto.getScholarshipId()).orElse(null);
        Student student = studentRepository.findById(applicationDto.getStudentId()).orElse(null);

        if(!scholarship.isActive()) return null;

        Application application = Application.builder()
                .studentId(student.getId())
                .scholarship(scholarship)
                .status("PENDIENTE")
                .feedback("En espera de respuesta.")
                .build();

        return buildApplicationDto(applicationRepository.save(application));
    }

    @Transactional
    public void changeApplicationStatus(Long id, String status) {
        Application application = applicationRepository.findById(id).orElse(null);

        if(status.equals("ACEPTADA")) {
            application.setStatus(status);
            application.setFeedback("Felicidades, tu solicitud ha sido aceptada");
        } else if(status.equals("RECHAZADA")) {
            application.setStatus(status);
            application.setFeedback("Lo sentimos, tu solicitud ha sido rechazada");
        }

        applicationRepository.save(application);
    }

    public ApplicationDto buildApplicationDto(Application application) {
        Student student = studentRepository.findById(application.getStudentId()).orElse(null);
        Person person = personRepository.findById(student.getPersonId()).orElse(null);
        List<Grade> grades = (List<Grade>) gradeRepository.findAllByStudentId(student.getId());
        Scholarship scholarship = scholarshipRepository.findById(application.getScholarship().getId()).orElse(null);
        Career career = careerRepository.findById(student.getCareer().getId()).orElse(null);
        Period period = periodRepository.findById(student.getPeriod().getId()).orElse(null);
        return ApplicationDto.builder()
                .applicationId(application.getId())
                .status(application.getStatus())
                .feedback(application.getFeedback())
                .studentId(student.getId())
                .enrollment(student.getEnrollment())
                .averageGrade(getAverageGrade(grades))
                .careerId(career.getId())
                .career(career.getName())
                .periodId(period.getId())
                .period(period.getStartYear())
                .personId(person.getId())
                .fullName(person.getFullName())
                .phone(person.getPhone())
                .curp(person.getCurp())
                .scholarshipId(scholarship.getId())
                .scholarship(scholarship.getName())
                .description(scholarship.getDescription())
                .active(scholarship.isActive())
                .build();
    }

    public float getAverageGrade(List<Grade> grades) {
        float sum = 0;
        for (Grade grade : grades) {
            sum += grade.getFirstPartial();
            sum += grade.getSecondPartial();
            sum += grade.getThirdPartial();
        }
        return Float.parseFloat(new DecimalFormat("0.00").format(sum / (grades.size()*3)));
    }
}
