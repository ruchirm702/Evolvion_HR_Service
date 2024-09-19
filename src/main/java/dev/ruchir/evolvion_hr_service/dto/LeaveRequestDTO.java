package dev.ruchir.evolvion_hr_service.dto;

import dev.ruchir.evolvion_hr_service.model.enums.LeaveRequestStatus;
import dev.ruchir.evolvion_hr_service.model.enums.LeaveType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LeaveRequestDTO {
    private Long id;
    private Long employeeId;
    private LeaveType leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveRequestStatus status;
}
