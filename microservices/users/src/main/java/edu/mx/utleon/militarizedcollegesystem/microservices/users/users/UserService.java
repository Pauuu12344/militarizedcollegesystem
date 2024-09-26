package edu.mx.utleon.militarizedcollegesystem.microservices.users.users;

import edu.mx.utleon.militarizedcollegesystem.common.dto.UserPersonDto;
import edu.mx.utleon.militarizedcollegesystem.common.entity.staff.Person;
import edu.mx.utleon.militarizedcollegesystem.common.entity.users.User;
import edu.mx.utleon.militarizedcollegesystem.microservices.users.staff.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PersonRepository personRepository;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public List<UserPersonDto> getAllUsers() {
        return ((List<User>) userRepository.findAll()).stream()
                .map(this::buildPersonDto)
                .collect(Collectors.toList());
    }

    private UserPersonDto buildPersonDto(User user) {
        Person person = personRepository.findById(user.getPersonId()).orElse(null);
        return UserPersonDto.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .active(user.isActive())
                .role(user.getRole().getName())
                .personId(person.getId())
                .fullName(person.getFullName())
                .phone(person.getPhone())
                .curp(person.getCurp())
                .build();
    }

}
