package edu.mx.utleon.militarizedcollegesystem.common.entities.scholarships;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean approved;

    @Column(nullable = false)
    private String feedback;

    private Long studentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scholarship_id", referencedColumnName = "id")
    private Scholarship scholarship;

}
