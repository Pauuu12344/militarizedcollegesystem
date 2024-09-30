package edu.mx.utleon.militarizedcollegesystem.microservices.academics.academics;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.SubjectDto;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public List<SubjectDto> getAllSubjects() {
        return ((List<Subject>) subjectRepository.findAll())
                .stream()
                .map(this::buildSubjectDto)
                .toList();
    }

    public List<SubjectDto> getAllSubjectsByCareerId(Long careerId) {
        return ((List<Subject>) subjectRepository.findAllByCareerId(careerId))
                .stream()
                .map(this::buildSubjectDto)
                .toList();
    }

    public SubjectDto buildSubjectDto(Subject subject) {
        return SubjectDto.builder()
                .subjectId(subject.getId())
                .name(subject.getName())
                .build();
    }


}
