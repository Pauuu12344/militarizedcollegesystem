package edu.mx.utleon.militarizedcollegesystem.common.entities.academics;

import edu.mx.utleon.militarizedcollegesystem.common.entities.staff.Employee;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_partial")
    private float firstPartial;

    @Column(name = "second_partial")
    private float secondPartial;

    @Column(name = "third_partial")
    private float thirdPartial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", referencedColumnName = "id", nullable = false)
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    private Group group;

}
