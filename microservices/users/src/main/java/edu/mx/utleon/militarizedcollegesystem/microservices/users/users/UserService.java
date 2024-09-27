package edu.mx.utleon.militarizedcollegesystem.microservices.users.users;

import edu.mx.utleon.militarizedcollegesystem.common.entities.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User updateAccount(User user) {
        User savedUser = userRepository.findById(user.getId()).get();
        System.out.println(user);
        if(!user.getPassword().isEmpty()) {
            savedUser.setPassword(user.getPassword());
        }
        savedUser.setUsername(user.getUsername());
        savedUser.setEmail(user.getEmail());
        savedUser.getPerson().setFullName(user.getPerson().getFullName());
        savedUser.getPerson().setCurp(user.getPerson().getCurp());
        savedUser.getPerson().setPhone(user.getPerson().getPhone());
        return userRepository.save(savedUser);
    }

}
