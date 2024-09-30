package edu.mx.utleon.militarizedcollegesystem.common.entities.academics;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToMany(mappedBy = "subjects")
    @JsonIgnore
    private Collection<Group> groups;

    @ManyToMany(mappedBy = "subjects")
    @JsonIgnore
    private Collection<Career> careers;

}
