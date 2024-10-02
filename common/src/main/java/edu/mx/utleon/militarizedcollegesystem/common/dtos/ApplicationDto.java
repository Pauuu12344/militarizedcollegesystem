package edu.mx.utleon.militarizedcollegesystem.common.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ApplicationDto {
    private Long applicationId;
    private String status;
    private String feedback;
    private Long studentId;
    private String enrollment;
    private float averageGrade;
    private Long careerId;
    private String career;
    private Long periodId;
    private int period;
    private Long personId;
    private String fullName;
    private Long phone;
    private String curp;
    private Long scholarshipId;
    private String scholarship;
    private String description;
    private boolean active;
}
