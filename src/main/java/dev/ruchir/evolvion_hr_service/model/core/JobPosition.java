package dev.ruchir.evolvion_hr_service.model.core;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "job_positions")
@Getter
@Setter
public class JobPosition extends BaseModel {

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "salary_range_min", nullable = false)
    private Double salaryRangeMin;

    @Column(name = "salary_range_max", nullable = false)
    private Double salaryRangeMax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}
