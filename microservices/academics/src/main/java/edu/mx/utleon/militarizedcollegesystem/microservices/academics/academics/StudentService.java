package edu.mx.utleon.militarizedcollegesystem.microservices.academics.academics;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.StudentDto;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Career;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Period;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Student;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Person;
import edu.mx.utleon.militarizedcollegesystem.microservices.academics.users.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private CareerRepository careerRepository;

    @Autowired
    private PeriodRepository periodRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<StudentDto> getAllStudents() {
        return ((List<Student>) studentRepository.findAll()).stream()
                .map(this::buildStudentDto)
                .collect(Collectors.toList());
    }

    public List<StudentDto> getAllStudentsByPeriodIdAndCareerId(Long periodId, Long careerId) {
        return ((List<Student>) studentRepository.findAllByPeriodIdAndCareerId(periodId, careerId)).stream()
                .map(this::buildStudentDto)
                .collect(Collectors.toList());
    }


    public StudentDto getStudentById(Long id) {
        return buildStudentDto(studentRepository.findById(id).orElse(null));
    }

    public StudentDto getStudentByPersonId(Long personId) {
        return buildStudentDto(studentRepository.findByPersonId(personId).orElse(null));
    }

    public StudentDto buildStudentDto(Student student) {
        Career career = careerRepository.findById(student.getCareer().getId()).orElse(null);
        Period period = periodRepository.findById(student.getPeriod().getId()).orElse(null);
        Person person = personRepository.findById(student.getPersonId()).orElse(null);
        return StudentDto.builder()
                .studentId(student.getId())
                .enrollment(student.getEnrollment())
                .careerId(career.getId())
                .career(career.getName())
                .periodId(period.getId())
                .period(period.getStartYear())
                .personId(student.getPersonId())
                .fullName(person.getFullName())
                .phone(person.getPhone())
                .curp(person.getCurp())
                .build();
    }

}
