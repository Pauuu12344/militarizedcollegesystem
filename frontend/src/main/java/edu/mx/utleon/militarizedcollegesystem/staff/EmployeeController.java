package edu.mx.utleon.militarizedcollegesystem.staff;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.EmployeeDto;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Role;
import edu.mx.utleon.militarizedcollegesystem.common.entities.users.Roles;
import edu.mx.utleon.militarizedcollegesystem.users.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.LinkedHashMap;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RoleService roleService;
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
        model.addAttribute("roles", roleService.getAllRoles());
        return "staff/employee-form";
    }

    @PostMapping("/employees/new")
    public String createEmployee(EmployeeDto employee) {
        employeeService.createEmployee(employee);
        return "redirect:/employees";
    }

}
