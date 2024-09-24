package edu.mx.utleon.militarizedcollegesystem.model.admissions;

import jakarta.persistence.*;
import lombok.*;

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

    private Long personId;

    private Long careerId;

    private Long periodId;

    @Column(nullable = false)
    private String status;

}
