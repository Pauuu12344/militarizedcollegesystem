package edu.mx.utleon.militarizedcollegesystem.microservices.users.users;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.UserPersonDto;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Person;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public List<UserPersonDto> getAllUsers() {
        return ((List<User>) userRepository.findAll()).stream()
                .map(this::buildPersonDto)
                .collect(Collectors.toList());
    }

    private UserPersonDto buildPersonDto(User user) {
        return UserPersonDto.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .active(user.isActive())
                .role(user.getRole().getName())
                .personId(user.getPerson().getId())
                .fullName(user.getPerson().getFullName())
                .phone(user.getPerson().getPhone())
                .curp(user.getPerson().getCurp())
                .build();
    }

}
