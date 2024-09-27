package edu.mx.utleon.militarizedcollegesystem.users;

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

    public User getByUsername(String username) {
        String path = USERS_URL + "user?username=" + username;
        return restTemplate.getForObject(path, User.class);
    }

    public User getUserById(Long id) {
        String path = USERS_URL + "user?id=" + id;
        return restTemplate.getForObject(path, User.class);
    }

    public List<User> getAllUsers() {
        String path = USERS_URL + "users";
        return restTemplate.getForObject(path, List.class);
    }

    public User updateAccount(User user) {
        String path = USERS_URL + "account";
        return restTemplate.postForObject(path, user, User.class);
    }
}