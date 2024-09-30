package edu.mx.utleon.militarizedcollegesystem.users;

import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Role;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${microservices.url.users}")
    private String USERS_URL;

    public List<Role> getAllRoles() {
        String path = USERS_URL + "roles";
        List<Role> roles = restTemplate.getForObject(path, List.class);
        roles.stream().filter(role -> role.getName().equals(Roles.ESTUDIANTE.name()));
        return roles;
    }

}
