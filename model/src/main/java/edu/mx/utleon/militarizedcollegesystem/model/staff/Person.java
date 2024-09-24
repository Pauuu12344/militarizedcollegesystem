package edu.mx.utleon.militarizedcollegesystem.model.staff;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private Long phone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, unique = true)
    private String curp;

}
