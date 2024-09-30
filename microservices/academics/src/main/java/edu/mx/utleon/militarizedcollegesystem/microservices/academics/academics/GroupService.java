package edu.mx.utleon.militarizedcollegesystem.microservices.academics.academics;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.GroupDto;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    public List<GroupDto> getAllGroups() {
        return ((List<Group>) groupRepository.findAll())
                .stream()
                .map(this::buildGroupDto)
                .toList();
    }

    public GroupDto buildGroupDto(Group group) {
        return GroupDto.builder()
                .groupId(group.getId())
                .name(group.getName())
                .students(group.getStudents().stream().map(studentService::buildStudentDto).toList())
                .subjects(group.getSubjects().stream().map(subjectService::buildSubjectDto).toList())
                .build();
    }

}
