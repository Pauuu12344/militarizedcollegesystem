package edu.mx.utleon.militarizedcollegesystem.microservices.academics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"edu.mx.utleon.militarizedcollegesystem.model.academics"})
public class Academics {

    public static void main(String[] args) {
        SpringApplication.run(Academics.class, args);
    }

}
