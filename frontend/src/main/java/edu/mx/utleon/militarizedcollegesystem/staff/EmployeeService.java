package edu.mx.utleon.militarizedcollegesystem.staff;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.EmployeeDto;
import edu.mx.utleon.militarizedcollegesystem.common.dtos.GradeDto;
import edu.mx.utleon.militarizedcollegesystem.common.entities.staff.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${microservices.url.staff}")
    private String STAFF_URL;

    public List<EmployeeDto> getAllEmployees() {
        String path = STAFF_URL + "employees";
        return restTemplate.getForObject(path, List.class);
    }

    public List<EmployeeDto> getAllEmployeesByArea(String area) {
        String path = STAFF_URL + "employees?area=" + area;
        return restTemplate.exchange(path, HttpMethod.GET, null, new ParameterizedTypeReference<List<EmployeeDto>>() {}).getBody();
    }

    public void createEmployee(EmployeeDto employee) {
        String path = STAFF_URL + "employees";
        restTemplate.postForObject(path, employee, EmployeeDto.class);
    }
    public EmployeeDto getEmployeeById(Long employeeId) {
        String path = STAFF_URL + "employees/" + employeeId;
        return restTemplate.getForObject(path, EmployeeDto.class);
    }
    public void updateEmployee(Long employeeId, EmployeeDto employee) {
        String path = STAFF_URL + employeeId;
        HttpEntity<EmployeeDto> requestEntity = new HttpEntity<>(employee);
        restTemplate.exchange(path, HttpMethod.PUT, requestEntity, Void.class);
    }

}
