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
    private Collection<StudentDto> students;
    private Collection<SubjectDto> subjects;
}
