package edu.mx.utleon.militarizedcollegesystem.microservices.users.users;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.UserPersonDto;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserPersonDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) return null;
        return buildUserPersonDto(user);
    }

    public UserPersonDto getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) return null;
        return buildUserPersonDto(user);
    }

    public List<UserPersonDto> getAllUsers() {
        return ((List<User>) userRepository.findAll()).stream().map(this::buildUserPersonDto).collect(Collectors.toList());
    }

    public UserPersonDto updateAccount(UserPersonDto user) {
        User savedUser = userRepository.findById(user.getUserId()).get();
        if (!user.getPassword().isEmpty()) {
            savedUser.setPassword(user.getPassword());
        }
        savedUser.setUsername(user.getUsername());
        savedUser.setEmail(user.getEmail());
        savedUser.getPerson().setFullName(user.getFullName());
        savedUser.getPerson().setCurp(user.getCurp());
        savedUser.getPerson().setPhone(user.getPhone());
        return buildUserPersonDto(userRepository.save(savedUser));
    }

    private UserPersonDto buildUserPersonDto(User user) {
        return UserPersonDto.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .active(user.isActive())
                .roleId(user.getRole().getId())
                .role(user.getRole().getName())
                .personId(user.getPerson().getId())
                .fullName(user.getPerson().getFullName())
                .phone(user.getPerson().getPhone())
                .curp(user.getPerson().getCurp())
                .build();
    }

}
