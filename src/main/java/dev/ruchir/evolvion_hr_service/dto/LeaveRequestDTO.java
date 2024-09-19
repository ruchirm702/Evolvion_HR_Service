package dev.ruchir.evolvion_hr_service.dto;

import dev.ruchir.evolvion_hr_service.model.enums.LeaveType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class LeaveRequestDTO {
    private Long id;

    @NotNull(message = "Employee ID cannot be null")
    private Long employeeId;

    @NotNull(message = "Leave type cannot be null")
    private LeaveType leaveType;

    @Past(message = "Start date must be in the past")
    @NotNull(message = "Start date cannot be null")
    private Date startDate;

    @Future(message = "End date must be in the future")
    @NotNull(message = "End date cannot be null")
    private Date endDate;

    @Size(max = 20, message = "Status should not exceed 20 characters")
    private String status; // PENDING, APPROVED, REJECTED
}
