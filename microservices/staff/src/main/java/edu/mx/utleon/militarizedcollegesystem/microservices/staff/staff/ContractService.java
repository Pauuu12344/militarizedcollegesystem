package edu.mx.utleon.militarizedcollegesystem.microservices.staff.staff;

import edu.mx.utleon.militarizedcollegesystem.common.entities.staff.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ContractService {
    @Autowired
    private  ContractRepository contractRepository;
    public List<Contract> getAllContract (){
        return (List<Contract>) contractRepository.findAll();
    }
}
