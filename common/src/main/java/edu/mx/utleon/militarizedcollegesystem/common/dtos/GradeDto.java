package edu.mx.utleon.militarizedcollegesystem.common.dtos;

import jakarta.persistence.Transient;
import lombok.*;

import java.text.DecimalFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GradeDto {
    private Long gradeId;
    private float firstPartial;
    private float secondPartial;
    private float thirdPartial;
    private Long subjectId;
    private String subject;
    private Long groupId;
    private String group;
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

    @Transient
    public float getAverage() {
        return Float.parseFloat(new DecimalFormat("0.00").format((firstPartial + secondPartial + thirdPartial) / 3));
    }
}
