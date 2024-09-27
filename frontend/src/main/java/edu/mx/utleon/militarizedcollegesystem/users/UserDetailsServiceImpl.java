package edu.mx.utleon.militarizedcollegesystem.users;

import edu.mx.utleon.militarizedcollegesystem.common.entities.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${microservices.url.users}")
    private String USERS_URL;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String path = USERS_URL + "user?username=" + username;
        User user = restTemplate.getForObject(path , User.class);
        if(user == null) throw new UsernameNotFoundException("Username not found");
        return new UserDetailsImpl(user);
    }

}
