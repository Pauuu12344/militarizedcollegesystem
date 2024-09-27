package edu.mx.utleon.militarizedcollegesystem.microservices.staff.staff;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.PersonnelPersonDto;
import edu.mx.utleon.militarizedcollegesystem.common.dtos.UserPersonDto;
import edu.mx.utleon.militarizedcollegesystem.common.entities.staff.Personnel;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonnelService {
    @Autowired
    private PersonnelRepository personnelRepository;
    public List<PersonnelPersonDto> getAllPersonnel() {
        return ((List<Personnel>) personnelRepository.findAll()).stream()
                .map(this::buildPersonnelPersonDto)
                .collect(Collectors.toList());
    }

    private PersonnelPersonDto buildPersonnelPersonDto(Personnel personnel) {
        return PersonnelPersonDto.builder()
                .id(personnel.getId())
                .number(personnel.getNumber())
                .startDate(personnel.getStartDate())
                .contract(personnel.getContract())
                .area(personnel.getArea())
                .personId(personnel.getPersonId())
                .build();
    }
}
