package edu.mx.utleon.militarizedcollegesystem.microservices.academics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({
        "edu.mx.utleon.militarizedcollegesystem.model.academics",
        "edu.mx.utleon.militarizedcollegesystem.model.staff",
        "edu.mx.utleon.militarizedcollegesystem.model.users"
})
public class Academics {

    public static void main(String[] args) {
        SpringApplication.run(Academics.class, args);
    }

}
