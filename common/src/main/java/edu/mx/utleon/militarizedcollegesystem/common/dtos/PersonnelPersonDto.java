package edu.mx.utleon.militarizedcollegesystem.common.dtos;

import edu.mx.utleon.militarizedcollegesystem.common.entities.staff.Area;
import edu.mx.utleon.militarizedcollegesystem.common.entities.staff.Contract;
import lombok.*;

import java.time.Instant;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PersonnelPersonDto {
    private Long personnelId;
    private String number;
    private String startDate;
    private String contract;
    private String area;
    private Long personId;
    private String fullName;
    private Long phone;
    private String curp;
}
