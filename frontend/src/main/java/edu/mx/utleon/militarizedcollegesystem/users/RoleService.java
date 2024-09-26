package edu.mx.utleon.militarizedcollegesystem.users;

import edu.mx.utleon.militarizedcollegesystem.common.dto.UserPersonDto;
import edu.mx.utleon.militarizedcollegesystem.common.entity.users.Roles;
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

    public List<Roles> getAllRoles() {
        String path = USERS_URL + "roles";
        return restTemplate.getForObject(path, List.class);
    }

}
