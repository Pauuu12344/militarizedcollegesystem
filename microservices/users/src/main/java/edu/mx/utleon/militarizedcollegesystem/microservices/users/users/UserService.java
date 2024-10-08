package edu.mx.utleon.militarizedcollegesystem.microservices.users.users;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.UserDto;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) return null;
        return buildUserDto(user);
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) return null;
        return buildUserDto(user);
    }

    public List<UserDto> getAllUsers() {
        return ((List<User>) userRepository.findAll())
                .stream()
                .map(this::buildUserDto)
                .toList();
    }

    public UserDto updateAccount(UserDto user) {
        User savedUser = userRepository.findById(user.getUserId()).get();
        if (!user.getPassword().isEmpty()) {
            savedUser.setPassword(user.getPassword());
        }
        savedUser.setUsername(user.getUsername());
        savedUser.setEmail(user.getEmail());
        savedUser.getPerson().setFullName(user.getFullName());
        savedUser.getPerson().setCurp(user.getCurp());
        savedUser.getPerson().setPhone(user.getPhone());
        return buildUserDto(userRepository.save(savedUser));
    }

    public boolean checkEmail(Long id, String email) {
        if (id != null) {
            User user = userRepository.findById(id).orElse(null);
            if (user.getEmail().equals(email)) return false;
        }
        return userRepository.findByEmail(email).isPresent();
    }

    private UserDto buildUserDto(User user) {
        return UserDto.builder()
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
