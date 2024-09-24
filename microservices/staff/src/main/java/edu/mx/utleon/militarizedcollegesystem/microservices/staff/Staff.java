package edu.mx.utleon.militarizedcollegesystem.microservices.staff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"edu.mx.utleon.militarizedcollegesystem.model.staff"})
public class Staff {

    public static void main(String[] args) {
        SpringApplication.run(Staff.class, args);
    }

}
