package edu.mx.utleon.militarizedcollegesystem.microservices.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EntityScan({
        "edu.mx.utleon.militarizedcollegesystem.common.entity.users",
        "edu.mx.utleon.militarizedcollegesystem.common.entity.staff",
        "edu.mx.utleon.militarizedcollegesystem.common.entity.academics"
})
public class Users {

    public static void main(String[] args) {
        SpringApplication.run(Users.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
