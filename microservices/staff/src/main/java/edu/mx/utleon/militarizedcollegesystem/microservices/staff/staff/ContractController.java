package edu.mx.utleon.militarizedcollegesystem.microservices.staff.staff;

import edu.mx.utleon.militarizedcollegesystem.common.entities.staff.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContractController {
    @Autowired
    private ContractService contractService;

    @GetMapping("/contracts")
    public List<Contract> getAllContract (){
        return contractService.getAllContract();
    }
}
