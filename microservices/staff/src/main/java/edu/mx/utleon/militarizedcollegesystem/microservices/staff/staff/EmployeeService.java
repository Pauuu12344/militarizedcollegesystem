package edu.mx.utleon.militarizedcollegesystem.microservices.staff.staff;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.ApplicantDto;
import edu.mx.utleon.militarizedcollegesystem.common.dtos.EmployeeDto;
import edu.mx.utleon.militarizedcollegesystem.common.dtos.UserDto;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Career;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Period;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Student;
import edu.mx.utleon.militarizedcollegesystem.common.entities.admissions.Applicant;
import edu.mx.utleon.militarizedcollegesystem.common.entities.admissions.Status;
import edu.mx.utleon.militarizedcollegesystem.common.entities.staff.Area;
import edu.mx.utleon.militarizedcollegesystem.common.entities.staff.Contract;
import edu.mx.utleon.militarizedcollegesystem.common.entities.staff.Employee;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Person;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Role;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Roles;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.User;
import edu.mx.utleon.militarizedcollegesystem.microservices.staff.users.PersonRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.staff.users.RoleRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.staff.users.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private AreaRepository areaRepository;

    public List<EmployeeDto> getAllEmployees() {
        return ((List<Employee>) employeeRepository.findAll())
                .stream()
                .map(this::buildEmployeeDto)
                .toList();
    }


    @Transactional
    public EmployeeDto createEmployee(EmployeeDto employeeDto){
        Person person = personRepository.save(
                Person.builder()
                        .fullName(employeeDto.getFullName())
                        .phone(employeeDto.getPhone())
                        .curp(employeeDto.getCurp())
                        .build()
        );
        Contract contract = contractRepository.findByType(employeeDto.getContract()).orElse(null);
        Area area = areaRepository.findByName(employeeDto.getArea()).orElse(null);
        Employee employee= employeeRepository.save(
                Employee.builder()
                        .contract(contract)
                        .startDate(Instant.now())
                        .area(area)
                        .personId(person.getId())
                        .build()
                
        );

        Role role = null;
        switch (employeeDto.getArea()) {
            case "PROFESORES":
                role = roleRepository.findByName(Roles.PROFESOR.name()).orElse(null);
                break;
            case "SERVICIOS_ESCOLARES":
                role = roleRepository.findByName(Roles.SERVICIOS_ESCOLARES.name()).orElse(null);
                break;
            case "TECNOLOGIAS_DE_LA_INFORMACION":
                role = roleRepository.findByName(Roles.TECNOLOGIAS_DE_LA_INFORMACION.name()).orElse(null);
                break;
            case "RECUSOS_HUMANOS":
                role = roleRepository.findByName(Roles.RECURSOS_HUMANOS.name()).orElse(null);
                break;
        }

       User user = userRepository.save(
               User.builder()
                       .person(person)
                       .role(role)
                       .username(employee.getNumber())
                       .password(employee.getNumber())
                       .email(employeeDto.getEmail())
                       .active(true)
                       .build()
       );
        return buildEmployeeDto(employee);
    }



    private EmployeeDto buildEmployeeDto(Employee employee) {
        User user = userRepository.findByPersonId(employee.getPersonId()).orElse(null);
        return EmployeeDto.builder()
                .employeeId(employee.getId())
                .number(employee.getNumber())
                .startDate(new SimpleDateFormat("dd/MM/yyyy").format(Date.from(employee.getStartDate())))
                .contract(employee.getContract().getType())
                .area(employee.getArea().getName())
                .userId(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .active(user.isActive())
                .roleId(user.getRole().getId())
                .role(user.getRole().getName())
                .personId(user.getPerson().getId())
                .fullName(user.getPerson().getFullName())
                .phone(user.getPerson().getPhone())
                .curp(user.getPerson().getCurp())
                .build();
        
    }

}
