package edu.mx.utleon.militarizedcollegesystem.microservices.users.configurations;

import edu.mx.utleon.militarizedcollegesystem.common.entity.academics.Career;
import edu.mx.utleon.militarizedcollegesystem.common.entity.academics.Period;
import edu.mx.utleon.militarizedcollegesystem.common.entity.academics.Student;
import edu.mx.utleon.militarizedcollegesystem.common.entity.staff.*;
import edu.mx.utleon.militarizedcollegesystem.microservices.users.academics.CareerRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.users.academics.PeriodRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.users.academics.StudentRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.users.staff.AreaRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.users.staff.ContractRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.users.staff.PersonRepository;
import edu.mx.utleon.militarizedcollegesystem.common.entity.users.Role;
import edu.mx.utleon.militarizedcollegesystem.common.entity.users.Roles;
import edu.mx.utleon.militarizedcollegesystem.common.entity.users.User;
import edu.mx.utleon.militarizedcollegesystem.microservices.users.staff.PersonnelRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.users.users.RoleRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.users.users.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class DataConfiguration {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonnelRepository personnelRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private CareerRepository careerRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PeriodRepository periodRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Role roleStudent = createRole(Roles.STUDENT.name());
        Role roleTeacher = createRole(Roles.TEACHER.name());
        Role roleHumanResources = createRole(Roles.HUMAN_RESOURCES.name());
        Role roleSchoolServices = createRole(Roles.SCHOOL_SERVICES.name());
        Role roleInformationTechnologies = createRole(Roles.INFORMATION_TECHNOLOGIES.name());


        Contract contractBase = createContract(Contracts.BASE.name());
        Contract contractFees = createContract(Contracts.FEES.name());

        Area areaTeachers = createArea(Areas.TEACHERS.name());
        Area areaHumanResources = createArea(Areas.HUMAN_RESOURCES.name());
        Area areaSchoolServices = createArea(Areas.SCHOOL_SERVICES.name());
        Area areaInformationTechnologies = createArea(Areas.INFORMATION_TECHNOLOGIES.name());

        Career careerAdministracionMilitar = createCareer("Administración Militar");
        Career careerSeguridadPublica = createCareer("Seguridad Pública");
        Career careerMedicoCirujanoMilitary = createCareer("Médico Cirujano Militar");
        Career careerIngenieriaMilitar = createCareer("Ingeniería Militar");
        Career careerTecnologiasInformacion = createCareer("Tecnologías de la Información");

        Period period2021 = createPeriod(Period.builder()
                .name("2021")
                .description("Perido 2021")
                .active(true)
                .build());

        Person personInformationTechnologies = createPerson(Person.builder()
                .curp("XXXX000000XXXXXXX4")
                .fullName("Information Technologies")
                .email("")
                .phone(4771234567L)
                .build());

        Person personStudent = createPerson(Person.builder()
                .curp("XXXX000000XXXXXXX0")
                .fullName("Student")
                .email("")
                .phone(4771234567L)
                .build());

        Person personTeacher = createPerson(Person.builder()
                .curp("XXXX000000XXXXXXX1")
                .fullName("Teacher")
                .email("")
                .phone(4771234567L)
                .build());

        Person personHumanResources = createPerson(Person.builder()
                .curp("XXXX000000XXXXXXX2")
                .fullName("Human Resources")
                .email("")
                .phone(4771234567L)
                .build());

        Person personSchoolServices = createPerson(Person.builder()
                .curp("XXXX000000XXXXXXX3")
                .fullName("School Services")
                .email("")
                .phone(4771234567L)
                .build());

        Personnel personnelInformationTechnologies = createPersonnel(Personnel.builder()
                .number("0000000000")
                .contract(contractBase)
                .startDate(Instant.now())
                .person(personInformationTechnologies)
                .area(areaInformationTechnologies)
                .build());

        Student studentStudent = createStudent(Student.builder()
                .enrollment("0000000000")
                .personId(personStudent.getId())
                .build());

        Personnel personnelTeacher = createPersonnel(Personnel.builder()
                .number("0000000001")
                .contract(contractBase)
                .startDate(Instant.now())
                .person(personTeacher)
                .area(areaTeachers)
                .build());

        Personnel personnelHumanResources = createPersonnel(Personnel.builder()
                .number("0000000002")
                .contract(contractBase)
                .startDate(Instant.now())
                .person(personHumanResources)
                .area(areaHumanResources)
                .build());

        Personnel personnelSchoolServices = createPersonnel(Personnel.builder()
                .number("0000000003")
                .contract(contractBase)
                .startDate(Instant.now())
                .person(personSchoolServices)
                .area(areaSchoolServices)
                .build());

        User userInformationTechnologies = createUser(User.builder()
                .username("information_technologies")
                .password(passwordEncoder.encode("information_technologies"))
                .email("")
                .active(true)
                .role(roleInformationTechnologies)
                .personId(personInformationTechnologies.getId())
                .build());

        User userStudent = createUser(User.builder()
                .username("student")
                .password(passwordEncoder.encode("student"))
                .email("")
                .active(true)
                .role(roleStudent)
                .personId(personStudent.getId())
                .build());

        User userTeacher = createUser(User.builder()
                .username("teacher")
                .password(passwordEncoder.encode("teacher"))
                .email("")
                .active(true)
                .role(roleTeacher)
                .personId(personTeacher.getId())
                .build());

        User userHumanResources = createUser(User.builder()
                .username("human_resources")
                .password(passwordEncoder.encode("human_resources"))
                .email("")
                .active(true)
                .role(roleHumanResources)
                .personId(personHumanResources.getId())
                .build());

        User userSchoolServices = createUser(User.builder()
                .username("school_services")
                .password(passwordEncoder.encode("school_services"))
                .email("")
                .active(true)
                .role(roleSchoolServices)
                .personId(personSchoolServices.getId())
                .build());
    }

    @Transactional
    protected Period createPeriod(Period period) {
        Period newPeriod = periodRepository.findByName(period.getName()).orElse(null);
        if (newPeriod == null)
            periodRepository.save(period);
        return newPeriod;
    }

    @Transactional
    protected Student createStudent(Student student) {
        Student newStudent = studentRepository.findByEnrollment(student.getEnrollment()).orElse(null);
        if (newStudent == null)
            newStudent = studentRepository.save(student);
        return newStudent;
    }

    @Transactional
    protected Career createCareer(String name) {
        Career newCareer = careerRepository.findByName(name).orElse(null);
        if (newCareer == null)
            newCareer = careerRepository.save(Career.builder().name(name).build());
        return newCareer;
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
    protected Role createRole(String name) {
        Role newRole = roleRepository.findByName(name).orElse(null);
        if (newRole == null)
            newRole = roleRepository.save(Role.builder().name(name).build());
        return newRole;
    }

    @Transactional
    public User createUser(User user) {
        User newUser = userRepository.findByUsername(user.getUsername()).orElse(null);
        if (newUser == null)
            newUser = userRepository.save(user);
        return newUser;
    }


}
