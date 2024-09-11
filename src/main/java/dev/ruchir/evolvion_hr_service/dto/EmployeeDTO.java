package dev.ruchir.evolvion_hr_service.dto;

import dev.ruchir.evolvion_hr_service.model.enums.EmployeeRole;
import dev.ruchir.evolvion_hr_service.model.enums.EmploymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private EmployeeRole role;
    private EmploymentStatus status;
    private Long departmentId; // Only storing department ID in DTO
}
