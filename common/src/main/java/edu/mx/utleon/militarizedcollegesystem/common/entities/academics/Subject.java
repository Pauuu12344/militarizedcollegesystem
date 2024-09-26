package edu.mx.utleon.militarizedcollegesystem.common.entities.academics;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "teacher_id")
    private Long teacherId;

    @ManyToMany(mappedBy = "subjects")
    private Collection<Group> groups;

}
