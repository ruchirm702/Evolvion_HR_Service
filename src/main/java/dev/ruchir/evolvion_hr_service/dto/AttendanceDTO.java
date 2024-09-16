package dev.ruchir.evolvion_hr_service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Getter
@Setter
public class AttendanceDTO {

    private Long id;

    @NotNull(message = "Employee ID cannot be null")
    @Positive(message = "Employee ID must be positive")
    private Long employeeId;

    @NotNull(message = "Check-in time cannot be null")
    private Date checkInTime;

    private Date checkOutTime;

    @NotNull(message = "Hours worked cannot be null")
    @Positive(message = "Hours worked must be positive")
    private Double hoursWorked;
}
