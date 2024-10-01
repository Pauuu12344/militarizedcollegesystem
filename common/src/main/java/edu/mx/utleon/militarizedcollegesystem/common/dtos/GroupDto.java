package edu.mx.utleon.militarizedcollegesystem.common.dtos;

import lombok.*;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GroupDto {
    private Long groupId;
    private String name;
    private Collection<Long> studentIds;
    private Collection<Long> subjectIds;
    private Collection<StudentDto> students;
    private Collection<SubjectDto> subjects;
}
