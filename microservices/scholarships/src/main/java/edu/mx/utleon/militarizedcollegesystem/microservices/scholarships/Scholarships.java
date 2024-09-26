package edu.mx.utleon.militarizedcollegesystem.microservices.scholarships;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({
        "edu.mx.utleon.militarizedcollegesystem.model.scholarships",
        "edu.mx.utleon.militarizedcollegesystem.model.academics",
        "edu.mx.utleon.militarizedcollegesystem.model.users"
})
public class Scholarships {

    public static void main(String[] args) {
        SpringApplication.run(Scholarships.class, args);
    }

}
