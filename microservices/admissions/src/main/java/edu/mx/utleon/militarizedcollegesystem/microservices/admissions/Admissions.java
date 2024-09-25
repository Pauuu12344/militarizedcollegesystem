package edu.mx.utleon.militarizedcollegesystem.microservices.admissions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"edu.mx.utleon.militarizedcollegesystem.model.admissions"})
public class Admissions {

    public static void main(String[] args) {
        SpringApplication.run(Admissions.class, args);
    }

}
