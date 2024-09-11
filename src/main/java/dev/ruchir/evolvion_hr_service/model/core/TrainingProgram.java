package dev.ruchir.evolvion_hr_service.model.core;

import dev.ruchir.evolvion_hr_service.model.enums.TrainingType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "training_programs")
@Getter
@Setter
public class TrainingProgram extends BaseModel {

    @Column(name = "program_name", nullable = false)
    private String programName;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TrainingType type;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private Date endDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "training_participants",
            joinColumns = @JoinColumn(name = "training_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> participants;
}
