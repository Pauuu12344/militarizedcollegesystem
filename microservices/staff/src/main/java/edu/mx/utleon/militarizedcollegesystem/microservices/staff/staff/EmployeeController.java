package edu.mx.utleon.militarizedcollegesystem.microservices.staff.staff;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.EmployeeDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<EmployeeDto> getAllEmployees(@RequestParam(required = false) String area) {
        if (area != null) {
            return employeeService.getEmployeesByArea(area);
        }
        return employeeService.getAllEmployees();
    }

    @PostMapping("/employees")
    public EmployeeDto saveEmployee(@RequestBody EmployeeDto employee) {return employeeService.saveEmployee(employee);}

    @GetMapping("/employees/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

}
