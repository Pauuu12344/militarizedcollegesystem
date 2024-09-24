package edu.mx.utleon.militarizedcollegesystem.microservices.scholarships;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"edu.mx.utleon.militarizedcollegesystem.model.scholarships"})
public class Scholarships {

    public static void main(String[] args) {
        SpringApplication.run(Scholarships.class, args);
    }

}
