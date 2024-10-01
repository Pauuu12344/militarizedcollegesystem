package edu.mx.utleon.militarizedcollegesystem.microservices.academics.configurations;

import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Career;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Period;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Student;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Subject;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Person;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Role;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Roles;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.User;
import edu.mx.utleon.militarizedcollegesystem.microservices.academics.academics.CareerRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.academics.academics.PeriodRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.academics.academics.StudentRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.academics.academics.SubjectRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.academics.users.PersonRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.academics.users.RoleRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.academics.users.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

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
    @Autowired
    private SubjectRepository subjectRepository;

    @Transactional
    @EventListener
    public void createData(ApplicationReadyEvent event) {
        Period period2024 = createPeriod(Period.builder()
                .startYear(2024)
                .description("16 semanas de clases, evaluaciones y actividades académicas")
                .active(true)
                .name("Periodo Sep-Dic 2024")  // Asegúrate de incluir el valor de nombre
                .build());


        Role roleEstudiante = createRole(Roles.ESTUDIANTE.name());

        Subject subjectMatematicas = createSubject("Matemáticas");
        Subject subjectFisica = createSubject("Física");
        Subject subjectQuimica = createSubject("Química");
        Subject subjectBiologia = createSubject("Biología");
        Subject subjectHistoria = createSubject("Historia");
        Subject subjectGeografia = createSubject("Geografía");
        Subject subjectProgramacion = createSubject("Programación");

        Career careerAdministracionMilitar = createCareer(Career
                .builder()
                .name("Administración Militar")
                .subjects(List.of(subjectMatematicas, subjectHistoria, subjectGeografia))
                .build()
        );
        Career careerSeguridadPublica = createCareer(Career
                .builder()
                .name("Seguridad Pública")
                .subjects(List.of(subjectMatematicas, subjectHistoria, subjectGeografia))
                .build()
        );
        Career careerMedicoCirujanoMilitary = createCareer(Career
                .builder()
                .name("Médico Cirujano Militar")
                .subjects(List.of(subjectMatematicas, subjectQuimica, subjectBiologia))
                .build()
        );
        Career careerIngenieriaMilitar = createCareer(Career
                .builder()
                .name("Ingeniería Militar")
                .subjects(List.of(subjectMatematicas, subjectFisica))
                .build()
        );

        Career careerTecnologiasInformacion = createCareer(Career
                .builder()
                .name("Tecnologías de la Información")
                .subjects(List.of(subjectMatematicas, subjectProgramacion))
                .build()
        );

        Person personStudent = createPerson(Person.builder()
                .curp("XXXX000000XXXXXXX0")
                .fullName("Student")
                .phone(4771234567L)
                .build());

        Student studentStudent = createStudent(Student.builder()
                .enrollment("0000000000")
                .career(careerAdministracionMilitar)
                .period(period2024)
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
        Period newPeriod = periodRepository.findByStartYear(period.getStartYear()).orElse(null);
        if (newPeriod == null)
            newPeriod = periodRepository.save(period);
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
    protected Career createCareer(Career career) {
        Career newCareer = careerRepository.findByName(career.getName()).orElse(null);
        if (newCareer == null)
            newCareer = careerRepository.save(career);
        return newCareer;
    }

    @Transactional
    protected Role createRole(String name) {
        Role newRole = roleRepository.findByName(name).orElse(null);
        if (newRole == null)
            newRole = roleRepository.save(Role.builder().name(name).build());
        return newRole;
    }

    @Transactional
    protected Subject createSubject(String name) {
        Subject newSubject = subjectRepository.findByName(name).orElse(null);
        if (newSubject == null) {
            newSubject = subjectRepository.save(Subject.builder().name(name).build());
        }
        return newSubject;
    }


}
