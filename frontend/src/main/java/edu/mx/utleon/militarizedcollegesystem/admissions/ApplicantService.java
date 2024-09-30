package edu.mx.utleon.militarizedcollegesystem.admissions;

import edu.mx.utleon.militarizedcollegesystem.academics.PeriodService;
import edu.mx.utleon.militarizedcollegesystem.common.dtos.ApplicantDto;
import edu.mx.utleon.militarizedcollegesystem.common.entities.academics.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApplicantService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${microservices.url.admissions}")
    private String ADMISSIONS_URL;

    @Value("${microservices.url.academics}")
    private String ACADEMICS_URL;

    @Autowired
    private PeriodService periodService;

    public ApplicantDto createApplicant(ApplicantDto applicantDto) {
        String path = ADMISSIONS_URL + "applicants";
        Period currentPeriod = periodService.getCurrentPeriod();
        applicantDto.setPeriodId(currentPeriod.getId());
        return restTemplate.postForObject(path, applicantDto, ApplicantDto.class);
    }

    public List<ApplicantDto> getAllPeriodApplicants() {
        Period currentPeriod = periodService.getCurrentPeriod();
        String path = ADMISSIONS_URL + "applicants?periodId=" + currentPeriod.getId();
        return restTemplate.getForObject(path, List.class);
    }

    public List<ApplicantDto> getAllApplicants() {
        String path = ADMISSIONS_URL + "applicants";
        return restTemplate.getForObject(path, List.class);
    }

    public void updateApplicantStatus(Long id, String status) {
        String path = ADMISSIONS_URL + "applicants?id=" + id + "&status=" + status;
        restTemplate.put(path, status);
    }
}
