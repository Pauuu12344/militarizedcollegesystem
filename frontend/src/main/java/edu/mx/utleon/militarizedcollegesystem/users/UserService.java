package edu.mx.utleon.militarizedcollegesystem.users;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.UserPersonDto;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${microservices.url.users}")
    private String USERS_URL;

    public UserPersonDto getByUsername(String username) {
        String path = USERS_URL + "user?username=" + username;
        return restTemplate.getForObject(path, UserPersonDto.class);
    }

    public UserPersonDto getUserById(Long id) {
        String path = USERS_URL + "user?id=" + id;
        return restTemplate.getForObject(path, UserPersonDto.class);
    }

    public List<UserPersonDto> getAllUsers() {
        String path = USERS_URL + "users";
        return restTemplate.getForObject(path, List.class);
    }

    public UserPersonDto updateAccount(UserPersonDto user) {
        String path = USERS_URL + "account";
        return restTemplate.postForObject(path, user, UserPersonDto.class);
    }
}