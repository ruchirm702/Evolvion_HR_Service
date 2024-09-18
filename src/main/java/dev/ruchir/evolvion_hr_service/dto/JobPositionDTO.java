package dev.ruchir.evolvion_hr_service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class JobPositionDTO {

    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Minimum salary is required")
    @Min(value = 0, message = "Minimum salary must be greater than or equal to 0")
    private Double salaryRangeMin;

    @NotNull(message = "Maximum salary is required")
    @Min(value = 0, message = "Maximum salary must be greater than or equal to 0")
    private Double salaryRangeMax;

    @NotNull(message = "Department ID is required")
    private Long departmentId;
}
