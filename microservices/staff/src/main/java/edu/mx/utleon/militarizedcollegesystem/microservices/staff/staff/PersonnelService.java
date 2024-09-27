package edu.mx.utleon.militarizedcollegesystem.microservices.staff.staff;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.PersonnelPersonDto;
import edu.mx.utleon.militarizedcollegesystem.common.dtos.UserPersonDto;
import edu.mx.utleon.militarizedcollegesystem.common.entities.staff.Personnel;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Person;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.User;
import edu.mx.utleon.militarizedcollegesystem.microservices.staff.users.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonnelService {
    @Autowired
    private PersonnelRepository personnelRepository;
    @Autowired
    private PersonRepository personRepository;

    public List<PersonnelPersonDto> getAllPersonnel() {
        return ((List<Personnel>) personnelRepository.findAll()).stream()
                .map(this::buildPersonnelPersonDto)
                .collect(Collectors.toList());
    }

    private PersonnelPersonDto buildPersonnelPersonDto(Personnel personnel) {
        Person person = personRepository.findById(personnel.getPersonId()).orElse(null);
        return PersonnelPersonDto.builder()
                .personnelId(personnel.getId())
                .number(personnel.getNumber())
                .startDate(new SimpleDateFormat("dd/MM/yyyy").format(Date.from(personnel.getStartDate())))
                .contract(personnel.getContract().getType())
                .area(personnel.getArea().getName())
                .personId(person.getId())
                .fullName(person.getFullName())
                .phone(person.getPhone())
                .curp(person.getCurp())
                .build();
    }
}
