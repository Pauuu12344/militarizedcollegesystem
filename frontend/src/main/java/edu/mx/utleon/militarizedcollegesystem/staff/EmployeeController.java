package edu.mx.utleon.militarizedcollegesystem.staff;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private ContractService contractService;

    @GetMapping("/employees")
    public String viewEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "staff/employees";
    }

    @GetMapping("/employees/new")
    public String viewNewEmployee(Model model) {
        model.addAttribute("employee", new EmployeeDto());
        model.addAttribute("contracts", contractService.getAllContracts());
        model.addAttribute("areas", areaService.getAllAreas());
        return "staff/employee-form";
    }

    @PostMapping("/employees/save")
    public String createEmployee(EmployeeDto employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/{id}")
    public String viewEditEmployee(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        model.addAttribute("contracts", contractService.getAllContracts());
        model.addAttribute("areas", areaService.getAllAreas());
        return "staff/employee-form";
    }

}

