package edu.mx.utleon.militarizedcollegesystem.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ValidatorService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${microservices.url.users}")
    private String USERS_URL;

    public boolean checkEmail(Long id, String email) {
        String path = USERS_URL;
        if (id != null) {
            path += "validate?id=" + id + "&email=" + email;
        } else {
            path += "validate?email=" + email;
        }
        return Boolean.TRUE.equals(restTemplate.postForObject(path, null, boolean.class));
    }

    public boolean checkCurp(Long id, String curp) {
        String path = USERS_URL;
        if (id != null) {
            path += "validate?id=" + id + "&curp=" + curp;
        } else {
            path += "validate?curp=" + curp;
        }
        return Boolean.TRUE.equals(restTemplate.postForObject(path, null, boolean.class));
    }
}
