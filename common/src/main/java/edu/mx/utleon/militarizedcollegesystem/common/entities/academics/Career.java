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
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "Career_Subject",
            joinColumns = @JoinColumn(name = "career_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id")
    )
    private Collection<Subject> subjects;
}
