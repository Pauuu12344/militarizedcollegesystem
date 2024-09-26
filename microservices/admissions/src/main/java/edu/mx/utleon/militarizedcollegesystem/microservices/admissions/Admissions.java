package edu.mx.utleon.militarizedcollegesystem.microservices.admissions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({
        "edu.mx.utleon.militarizedcollegesystem.model.admissions",
        "edu.mx.utleon.militarizedcollegesystem.model.academics",
        "edu.mx.utleon.militarizedcollegesystem.model.users"
})
public class Admissions {

    public static void main(String[] args) {
        SpringApplication.run(Admissions.class, args);
    }

}
