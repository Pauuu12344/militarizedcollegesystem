package edu.mx.utleon.militarizedcollegesystem.microservices.staff.staff;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.EmployeePersonDto;
import edu.mx.utleon.militarizedcollegesystem.common.entities.staff.Employee;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Person;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.User;
import edu.mx.utleon.militarizedcollegesystem.microservices.staff.users.PersonRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.staff.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private UserRepository userRepository;

    public List<EmployeePersonDto> getAllEmployees() {
        return ((List<Employee>) employeeRepository.findAll()).stream()
                .map(this::buildEmployeePersonDto)
                .collect(Collectors.toList());
    }

    private EmployeePersonDto buildEmployeePersonDto(Employee employee) {
        Person person = personRepository.findById(employee.getPersonId()).orElse(null);
        User user = userRepository.findByPersonId(employee.getPersonId()).orElse(null);
        return EmployeePersonDto.builder()
                .employeeId(employee.getId())
                .number(employee.getNumber())
                .startDate(new SimpleDateFormat("dd/MM/yyyy").format(Date.from(employee.getStartDate())))
                .contract(employee.getContract().getType())
                .area(employee.getArea().getName())
                .personId(person.getId())
                .fullName(person.getFullName())
                .phone(person.getPhone())
                .curp(person.getCurp())
                .userId(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .active(user.isActive())
                .build();
    }
}
