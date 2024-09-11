package dev.ruchir.evolvion_hr_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobPositionDTO {
    private Long id;
    private String title;
    private String description;
    private Double salaryRangeMin;
    private Double salaryRangeMax;
    private Long departmentId;
}
