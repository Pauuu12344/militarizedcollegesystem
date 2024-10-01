package edu.mx.utleon.militarizedcollegesystem.microservices.academics.academics;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.GroupDto;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Group;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Student;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Subject;
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

    public List<GroupDto> getAllGroups() {
        return ((List<Group>) groupRepository.findAll())
                .stream()
                .map(this::buildGroupDto)
                .toList();
    }

    @Transactional
    public GroupDto createGroup(GroupDto group) {
        Collection<Student> students = group.getStudentIds().stream()
                .map(studentId -> studentRepository.findById(studentId).orElse(null))
                .toList();

        Collection<Subject> subjects = group.getSubjectIds().stream()
                .map(subjectId -> subjectRepository.findById(subjectId).orElse(null))
                .toList();

        Group newGroup = groupRepository.save(Group.builder()
                .name(group.getName())
                .students(students)
                .subjects(subjects)
                .build());

        return buildGroupDto(newGroup);
    }

    public GroupDto buildGroupDto(Group group) {
        return GroupDto.builder()
                .groupId(group.getId())
                .name(group.getName())
                .studentIds(group.getStudents().stream().map(student -> student.getId()).toList())
                .subjectIds(group.getSubjects().stream().map(subject -> subject.getId()).toList())
                .students(group.getStudents().stream().map(studentService::buildStudentDto).toList())
                .subjects(group.getSubjects().stream().map(subjectService::buildSubjectDto).toList())
                .build();
    }

}
