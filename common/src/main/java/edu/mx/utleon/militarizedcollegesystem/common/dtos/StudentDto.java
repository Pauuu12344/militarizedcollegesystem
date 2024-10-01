package edu.mx.utleon.militarizedcollegesystem.common.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StudentDto {
    private Long studentId;
    private String enrollment;
    private Long careerId;
    private String career;
    private Long periodId;
    private int period;
    private Long personId;
    private String fullName;
    private Long phone;
    private String curp;
}
