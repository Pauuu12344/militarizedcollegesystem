package edu.mx.utleon.militarizedcollegesystem.microservices.academics.academics;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.GradeDto;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.*;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Person;
import edu.mx.utleon.militarizedcollegesystem.microservices.academics.users.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PeriodRepository periodRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CareerRepository careerRepository;

    public List<GradeDto> getAllGrades() {
        return ((List<Grade>) gradeRepository.findAll()).stream().map(this::buildGradeDto).toList();
    }

    public List<GradeDto> getGradesByStudentPersonId(Long personId) {
        return ((List<Grade>) gradeRepository.findByStudentPersonId(personId)).stream().map(this::buildGradeDto).toList();
    }

    public List<GradeDto> getGradesByGroupId(Long groupId) {
        return ((List<Grade>) gradeRepository.findByGroupId(groupId)).stream().map(this::buildGradeDto).toList();
    }

    private GradeDto buildGradeDto(Grade grade) {
        Subject subject = subjectRepository.findById(grade.getSubject().getId()).orElse(null);
        Group group = groupRepository.findById(grade.getGroup().getId()).orElse(null);
        Student student = studentRepository.findById(grade.getStudent().getId()).orElse(null);
        Period period = periodRepository.findById(student.getPeriod().getId()).orElse(null);
        Person person = personRepository.findById(student.getPersonId()).orElse(null);
        Career career = careerRepository.findById(student.getCareer().getId()).orElse(null);
        return GradeDto.builder()
                .gradeId(grade.getId())
                .firstPartial(grade.getFirstPartial())
                .secondPartial(grade.getSecondPartial())
                .thirdPartial(grade.getThirdPartial())
                .subjectId(subject.getId())
                .subject(subject.getName())
                .groupId(group.getId())
                .group(group.getName())
                .studentId(student.getId())
                .enrollment(student.getEnrollment())
                .careerId(career.getId())
                .career(career.getName())
                .periodId(period.getId())
                .period(period.getStartYear())
                .personId(person.getId())
                .fullName(person.getFullName())
                .phone(person.getPhone())
                .curp(person.getCurp())
                .build();
    }

    public void updateGrade(GradeDto gradeDto) {
        Grade grade = gradeRepository.findById(gradeDto.getGradeId()).orElse(null);
        if (grade != null) {
            grade.setFirstPartial(gradeDto.getFirstPartial());
            grade.setSecondPartial(gradeDto.getSecondPartial());
            grade.setThirdPartial(gradeDto.getThirdPartial());
            gradeRepository.save(grade);
        }
    }
}
