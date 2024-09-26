package edu.mx.utleon.militarizedcollegesystem.microservices.academics.configurations;

import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Career;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Period;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Student;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Person;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Role;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Roles;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.User;
import edu.mx.utleon.militarizedcollegesystem.microservices.academics.academics.CareerRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.academics.academics.PeriodRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.academics.academics.StudentRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.academics.users.PersonRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.academics.users.RoleRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.academics.users.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DataConfiguration {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CareerRepository careerRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PeriodRepository periodRepository;

    @Transactional
    @EventListener
    public void createData(ApplicationReadyEvent event) {
        Role roleEstudiante = createRole(Roles.ESTUDIANTE.name());

        Career careerAdministracionMilitar = createCareer("Administración Militar");
        Career careerSeguridadPublica = createCareer("Seguridad Pública");
        Career careerMedicoCirujanoMilitary = createCareer("Médico Cirujano Militar");
        Career careerIngenieriaMilitar = createCareer("Ingeniería Militar");
        Career careerTecnologiasInformacion = createCareer("Tecnologías de la Información");

        Period period2024 = createPeriod(Period.builder()
                .name("2024")
                .description("Perido 2024")
                .active(true)
                .build());

        Person personStudent = createPerson(Person.builder()
                .curp("XXXX000000XXXXXXX0")
                .fullName("Student")
                .email("")
                .phone(4771234567L)
                .build());

        Student studentStudent = createStudent(Student.builder()
                .enrollment("0000000000")
                .personId(personStudent.getId())
                .build());

        User userStudent = createUser(User.builder()
                .username("student")
                .password("student")
                .email("")
                .active(true)
                .role(roleEstudiante)
                .person(personStudent)
                .build());

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
    protected Role createRole(String name) {
        Role newRole = roleRepository.findByName(name).orElse(null);
        if (newRole == null)
            newRole = roleRepository.save(Role.builder().name(name).build());
        return newRole;
    }


}
