package edu.mx.utleon.militarizedcollegesystem.common.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EmployeeDto {
    private Long employeeId;
    private String number;
    private String startDate;
    private String contract;
    private String area;
    private Long personId;
    private String fullName;
    private Long phone;
    private String curp;
    private Long userId;
    private String username;
    private String password;
    private String email;
    private boolean active;
}
