package edu.mx.utleon.militarizedcollegesystem.microservices.academics.academics;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.GroupDto;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Grade;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Group;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Student;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Subject;
import edu.mx.utleon.militarizedcollegesystem.common.entities.staff.Employee;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Person;
import edu.mx.utleon.militarizedcollegesystem.microservices.academics.staff.EmployeeRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.academics.users.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PersonRepository personRepository;

    public List<GroupDto> getAllGroups() {
        return ((List<Group>) groupRepository.findAll()).stream().map(this::buildGroupDto).toList();
    }

    public List<GroupDto> getAllGroupsByPeriodIdAndCareerId(Long periodId, Long careerId) {
        return ((List<Group>) groupRepository.findGroupsByPeriodIdAndCareerId(periodId, careerId)).stream().map(this::buildGroupDto).toList();
    }

    @Transactional
    public GroupDto createGroup(GroupDto group) {
        Collection<Student> students = group.getStudentIds().stream().map(studentId -> studentRepository.findById(studentId).orElse(null)).toList();

        Collection<Subject> subjects = group.getSubjectIds().stream().map(subjectId -> subjectRepository.findById(subjectId).orElse(null)).toList();

        Employee teacher = employeeRepository.findById(group.getTeacherId()).orElse(null);

        Group newGroup = groupRepository.save(Group.builder().name(group.getName()).students(students).subjects(subjects).teacherId(teacher.getId()).build());

        students.forEach(student -> {
            subjects.forEach(subject -> {
                gradeRepository.save(Grade.builder().student(student).subject(subject).group(newGroup).build());
            });
        });

        return buildGroupDto(newGroup);
    }

    public GroupDto getGroupById(Long id) {
        return groupRepository.findById(id).map(this::buildGroupDto).orElse(null);
    }

    public GroupDto buildGroupDto(Group group) {
        Employee employeeTeacher = employeeRepository.findById(group.getTeacherId()).orElse(null);
        Person personTeacher = personRepository.findById(employeeTeacher.getPersonId()).orElse(null);
        return GroupDto.builder().groupId(group.getId()).name(group.getName()).teacherId(employeeTeacher.getId()).teacherNumber(employeeTeacher.getNumber()).teacher(personTeacher.getFullName()).studentIds(group.getStudents().stream().map(Student::getId).toList()).subjectIds(group.getSubjects().stream().map(Subject::getId).toList()).students(group.getStudents().stream().map(studentService::buildStudentDto).toList()).subjects(group.getSubjects().stream().map(subjectService::buildSubjectDto).toList()).build();
    }

}
