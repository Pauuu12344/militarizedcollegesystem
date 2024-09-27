package edu.mx.utleon.militarizedcollegesystem.microservices.staff.configurations;

import edu.mx.utleon.militarizedcollegesystem.common.entities.staff.*;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Person;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Role;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Roles;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.User;
import edu.mx.utleon.militarizedcollegesystem.microservices.staff.staff.AreaRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.staff.staff.ContractRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.staff.users.PersonRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.staff.staff.PersonnelRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.staff.users.RoleRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.staff.users.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class DataConfigurations {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private PersonnelRepository personnelRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Transactional
    @EventListener
    public void createData(ApplicationReadyEvent event) {
        Role roleProfesor = createRole(Roles.PROFESOR.name());
        Role roleRecursosHumanos = createRole(Roles.RECURSOS_HUMANOS.name());
        Role roleServiciosEscolares = createRole(Roles.SERVICIOS_ESCOLARES.name());
        Role roleTecnologiasDeLaInformacion = createRole(Roles.TECNOLOGIAS_DE_LA_INFORMACION.name());

        Contract contractBase = createContract(Contracts.BASE.name());
        Contract contractHonorarios = createContract(Contracts.HONORARIOS.name());

        Area areaProfesores = createArea(Areas.PROFESORES.name());
        Area areaRecursosHumanos = createArea(Areas.RECURSOS_HUMANOS.name());
        Area areaServiciosEscolares = createArea(Areas.SERVICIOS_ESCOLARES.name());
        Area areaTecnologiasDeLaInformacion = createArea(Areas.TECNOLOGIAS_DE_LA_INFORMACION.name());

        Person personInformationTechnologies = createPerson(Person.builder()
                .curp("XXXX000000XXXXXXX4")
                .fullName("Information Technologies")
                .phone(4771234567L)
                .build());

        Person personTeacher = createPerson(Person.builder()
                .curp("XXXX000000XXXXXXX1")
                .fullName("Teacher")
                .phone(4771234567L)
                .build());

        Person personHumanResources = createPerson(Person.builder()
                .curp("XXXX000000XXXXXXX2")
                .fullName("Human Resources")
                .phone(4771234567L)
                .build());

        Person personSchoolServices = createPerson(Person.builder()
                .curp("XXXX000000XXXXXXX3")
                .fullName("School Services")
                .phone(4771234567L)
                .build());

        Personnel personnelInformationTechnologies = createPersonnel(Personnel.builder()
                .contract(contractBase)
                .startDate(Instant.now())
                .personId(personInformationTechnologies.getId())
                .area(areaTecnologiasDeLaInformacion)
                .build());


        Personnel personnelTeacher = createPersonnel(Personnel.builder()
                .contract(contractBase)
                .startDate(Instant.now())
                .personId(personTeacher.getId())
                .area(areaProfesores)
                .build());

        Personnel personnelHumanResources = createPersonnel(Personnel.builder()
                .contract(contractBase)
                .startDate(Instant.now())
                .personId(personHumanResources.getId())
                .area(areaRecursosHumanos)
                .build());

        Personnel personnelSchoolServices = createPersonnel(Personnel.builder()
                .contract(contractBase)
                .startDate(Instant.now())
                .personId(personSchoolServices.getId())
                .area(areaServiciosEscolares)
                .build());


        User userInformationTechnologies = createUser(User.builder()
                .username("information_technologies")
                .password("information_technologies")
                .email("")
                .active(true)
                .role(roleTecnologiasDeLaInformacion)
                .person(personInformationTechnologies)
                .build());

        User userTeacher = createUser(User.builder()
                .username("teacher")
                .password("teacher")
                .email("")
                .active(true)
                .role(roleProfesor)
                .person(personTeacher)
                .build());

        User userHumanResources = createUser(User.builder()
                .username("human_resources")
                .password("human_resources")
                .email("")
                .active(true)
                .role(roleRecursosHumanos)
                .person(personHumanResources)
                .build());

        User userSchoolServices = createUser(User.builder()
                .username("school_services")
                .password("school_services")
                .email("")
                .active(true)
                .role(roleServiciosEscolares)
                .person(personSchoolServices)
                .build());

    }

    @Transactional
    protected Area createArea(String name) {
        Area newArea = areaRepository.findByName(name).orElse(null);
        if (newArea == null)
            newArea = areaRepository.save(Area.builder().name(name).build());
        return newArea;
    }

    @Transactional
    protected Contract createContract(String type) {
        Contract newContract = contractRepository.findByType(type).orElse(null);
        if (newContract == null)
            newContract = contractRepository.save(Contract.builder().type(type).build());
        return newContract;
    }

    @Transactional
    protected Personnel createPersonnel(Personnel personnel) {
        Personnel newPersonnel = personnelRepository.findByNumber(personnel.getNumber()).orElse(null);
        if (newPersonnel == null)
            newPersonnel = personnelRepository.save(personnel);
        return newPersonnel;
    }

    @Transactional
    protected Person createPerson(Person person) {
        Person newPerson = personRepository.findByCurp(person.getCurp()).orElse(null);
        if (newPerson == null)
            newPerson = personRepository.save(person);
        return newPerson;
    }

    @Transactional
    public User createUser(User user) {
        User newUser = userRepository.findByUsername(user.getUsername()).orElse(null);
        if (newUser == null)
            newUser = userRepository.save(user);
        return newUser;
    }

    @Transactional
    protected Role createRole(String name) {
        Role newRole = roleRepository.findByName(name).orElse(null);
        if (newRole == null)
            newRole = roleRepository.save(Role.builder().name(name).build());
        return newRole;
    }

}
