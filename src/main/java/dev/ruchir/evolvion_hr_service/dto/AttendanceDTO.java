package dev.ruchir.evolvion_hr_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AttendanceDTO {
    private Long id;
    private Long employeeId;
    private Date checkInTime;
    private Date checkOutTime;
    private Double hoursWorked;
}
