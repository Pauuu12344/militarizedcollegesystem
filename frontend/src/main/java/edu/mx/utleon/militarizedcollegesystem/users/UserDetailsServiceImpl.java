package edu.mx.utleon.militarizedcollegesystem.users;

import edu.mx.utleon.militarizedcollegesystem.model.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final RestTemplate restTemplate;

    @Value("${microservices.url.users}")
    private String USERS_URL;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String path = USERS_URL + username;
        User user = restTemplate.getForObject(path , User.class);
        if(user == null) throw new UsernameNotFoundException("Username not found");
        return new UserDetailsImpl(user);
    }

}
