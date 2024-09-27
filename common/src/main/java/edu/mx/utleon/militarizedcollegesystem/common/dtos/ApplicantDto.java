package edu.mx.utleon.militarizedcollegesystem.common.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ApplicantDto {
    private Long applicantId;
    private String enrollment;
    private Long personId;
    private String fullName;
    private Long phone;
    private String curp;
    private Long careerId;
    private String career;
    private Long periodId;
    private String period;
    private boolean status;
}