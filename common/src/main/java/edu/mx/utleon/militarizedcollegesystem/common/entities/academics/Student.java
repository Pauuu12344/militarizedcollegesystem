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
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String enrollment;

    @Column(nullable = false, name = "person_id")
    private Long personId;

    @ManyToMany(mappedBy = "students")
    private Collection<Group> groups;

}
