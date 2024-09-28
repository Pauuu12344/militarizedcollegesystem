package edu.mx.utleon.militarizedcollegesystem.admissions;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.ApplicantDto;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApplicantService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${microservices.url.admissions}")
    private String ADMISSIONS_URL;

    @Value("${microservices.url.academics}")
    private String ACADEMICS_URL;

    public ApplicantDto createApplicant(ApplicantDto applicantDto) {
        String path = ADMISSIONS_URL + "applicants";
        Period currentPeriod = getCurrentPeriod();
        applicantDto.setPeriodId(currentPeriod.getId());
        return restTemplate.postForObject(path, applicantDto, ApplicantDto.class);
    }

    public Period getCurrentPeriod() {
        String path = ACADEMICS_URL + "period";
        return restTemplate.getForObject(path, Period.class);
    }

}
