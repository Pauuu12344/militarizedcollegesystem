package edu.mx.utleon.militarizedcollegesystem.microservices.scholarships.configurations;

import edu.mx.utleon.militarizedcollegesystem.common.entities.scholarships.Scholarship;
import edu.mx.utleon.militarizedcollegesystem.microservices.scholarships.scholarships.ScholarshipRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DataConfiguration {

    @Autowired
    private ScholarshipRepository scholarshipRepository;

    @Transactional
    @EventListener
    public void createData(ApplicationReadyEvent event) {
        Scholarship academicExcellence = createScholarship(Scholarship.builder()
                .name("Académica")
                .description("Esta beca es otorgada a los estudiantes con el mejor promedio de la carrera.")
                .active(true)
                .build());

        Scholarship athleticExcellence = createScholarship(Scholarship.builder()
                .name("Deportiva")
                .description("Esta beca es otorgada a los estudiantes con el mejor desempeño en competencias deportivas.")
                .active(true)
                .build());

        Scholarship culturalExcellence = createScholarship(Scholarship.builder()
                .name("Cultural")
                .description("Esta beca es otorgada a los estudiantes con el mejor desempeño en actividades culturales.")
                .active(true)
                .build());
    }

    @Transactional
    protected Scholarship createScholarship(Scholarship scholarship) {
        Scholarship newScholarship = scholarshipRepository.findByName(scholarship.getName()).orElse(null);
        if (newScholarship == null)
            newScholarship = scholarshipRepository.save(scholarship);
        return newScholarship;
    }

}
