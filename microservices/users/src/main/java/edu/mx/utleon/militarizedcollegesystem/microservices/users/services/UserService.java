package edu.mx.utleon.militarizedcollegesystem.microservices.users.services;


import edu.mx.utleon.militarizedcollegesystem.microservices.users.repositories.UserRepository;
import edu.mx.utleon.militarizedcollegesystem.model.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

}
