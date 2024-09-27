package edu.mx.utleon.militarizedcollegesystem.common.entities.staff;

import org.apache.commons.lang3.RandomStringUtils;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Personnel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 12)
    private String number;

    @Column(nullable = false, name = "start_date")
    private Instant startDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_id", referencedColumnName = "id")
    private Contract contract;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private Area area;

    @Column(nullable = false, name = "person_id")
    private Long personId;

    @PrePersist
    private void generateUniqueNumber() {
        this.number = RandomStringUtils.randomAlphanumeric(12);
    }

}
