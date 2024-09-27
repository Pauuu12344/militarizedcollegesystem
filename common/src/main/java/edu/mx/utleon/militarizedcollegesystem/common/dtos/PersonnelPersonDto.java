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
    private Long id;
    private String number;
    private Instant startDate;
    private Contract contract;
    private Area area;
    private Long personId;

}
