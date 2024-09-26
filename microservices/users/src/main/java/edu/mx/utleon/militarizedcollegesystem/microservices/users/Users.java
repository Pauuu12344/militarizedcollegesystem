package edu.mx.utleon.militarizedcollegesystem.microservices.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({
        "edu.mx.utleon.militarizedcollegesystem.common.entities.users"
})
public class Users {

    public static void main(String[] args) {
        SpringApplication.run(Users.class, args);
    }

}
