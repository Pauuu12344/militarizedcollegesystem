package edu.mx.utleon.militarizedcollegesystem.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PersonService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${microservices.url.users}")
    private String USERS_URL;

    public boolean checkCurp(String curp){
        System.out.println(curp);
        String path= USERS_URL + "validate?curp=" + curp;
        return Boolean.TRUE.equals(restTemplate.postForObject(path, null, boolean.class));
    }

}
