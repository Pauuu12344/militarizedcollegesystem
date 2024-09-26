package edu.mx.utleon.militarizedcollegesystem.common.entities.admissions;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String enrollment;

    @Column(nullable = false, name = "person_id")
    private Long personId;

    @Column(nullable = false, name = "career_id")
    private Long careerId;

    @Column(nullable = false, name = "period_id")
    private Long periodId;

    @Column(nullable = false)
    private String status;

    @PrePersist
    private void generateEnrollment() {
        this.enrollment = RandomStringUtils.randomAlphanumeric(8);
    }

}
