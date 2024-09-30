package edu.mx.utleon.militarizedcollegesystem.academics;

import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PeriodService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${microservices.url.admissions}")
    private String ADMISSIONS_URL;

    @Value("${microservices.url.academics}")
    private String ACADEMICS_URL;

    public Period getCurrentPeriod() {
        String path = ACADEMICS_URL + "period";
        return restTemplate.getForObject(path, Period.class);
    }

    public List<Period> getAllPeriods() {
        String path = ACADEMICS_URL + "periods";
        return restTemplate.getForObject(path, List.class);
    }

    public Period getPeriodById(Long periodId) {
        String path = ACADEMICS_URL + "period?id=" + periodId;
        return restTemplate.getForObject(path, Period.class);
    }
}
