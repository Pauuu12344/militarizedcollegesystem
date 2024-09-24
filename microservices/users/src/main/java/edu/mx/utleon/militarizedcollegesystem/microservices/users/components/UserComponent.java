package edu.mx.utleon.militarizedcollegesystem.microservices.users.components;

import edu.mx.utleon.militarizedcollegesystem.microservices.users.repositories.RoleRepository;
import edu.mx.utleon.militarizedcollegesystem.microservices.users.repositories.UserRepository;
import edu.mx.utleon.militarizedcollegesystem.model.users.Role;
import edu.mx.utleon.militarizedcollegesystem.model.users.Roles;
import edu.mx.utleon.militarizedcollegesystem.model.users.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserComponent {


    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Role roleStudent = createRole(Roles.ROLE_STUDENT.name());
        Role roleTeacher = createRole(Roles.ROLE_TEACHER.name());
        Role roleHumanResources = createRole(Roles.ROLE_HUMAN_RESOURCES.name());
        Role roleSchoolServices = createRole(Roles.ROLE_SCHOOL_SERVICES.name());
        Role roleInformationTechnologies = createRole(Roles.ROLE_INFORMATION_TECHNOLOGIES.name());

        User informationTechnologies = createUser(User.builder()
                .username("information_technologies")
                .password(passwordEncoder.encode("information_technologies"))
                .email("")
                .active(true)
                .role(roleInformationTechnologies)
                .build());

        User student = createUser(User.builder()
                .username("student")
                .password(passwordEncoder.encode("student"))
                .email("")
                .active(true)
                .role(roleStudent)
                .build());

        User teacher = createUser(User.builder()
                .username("teacher")
                .password(passwordEncoder.encode("teacher"))
                .email("")
                .active(true)
                .role(roleTeacher)
                .build());

        User humanResources = createUser(User.builder()
                .username("human_resources")
                .password(passwordEncoder.encode("human_resources"))
                .email("")
                .active(true)
                .role(roleHumanResources)
                .build());

        User schoolServices = createUser(User.builder()
                .username("school_services")
                .password(passwordEncoder.encode("school_services"))
                .email("")
                .active(true)
                .role(roleSchoolServices)
                .build());
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
