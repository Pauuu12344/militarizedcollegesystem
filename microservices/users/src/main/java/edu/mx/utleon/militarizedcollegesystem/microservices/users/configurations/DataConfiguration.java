package edu.mx.utleon.militarizedcollegesystem.microservices.users.configurations;

import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Role;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Roles;
import edu.mx.utleon.militarizedcollegesystem.microservices.users.users.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DataConfiguration {

    @Autowired
    private RoleRepository roleRepository;


    @Transactional
    @EventListener
    public void createData(ApplicationReadyEvent event) {
        Role roleEstudiante = createRole(Roles.ESTUDIANTE.name());
        Role roleProfesor = createRole(Roles.PROFESOR.name());
        Role roleRecursosHumanos = createRole(Roles.RECURSOS_HUMANOS.name());
        Role roleServiciosEscolares = createRole(Roles.SERVICIOS_ESCOLARES.name());
        Role roleTecnologiasDeLaInformacion = createRole(Roles.TECNOLOGIAS_DE_LA_INFORMACION.name());
    }

    @Transactional
    protected Role createRole(String name) {
        Role newRole = roleRepository.findByName(name).orElse(null);
        if (newRole == null)
            newRole = roleRepository.save(Role.builder().name(name).build());
        return newRole;
    }


}
