package dev.ruchir.evolvion_hr_service.dto;

import dev.ruchir.evolvion_hr_service.model.enums.EmployeeRole;
import dev.ruchir.evolvion_hr_service.model.enums.EmploymentStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotNull(message = "Role is required")
    private EmployeeRole role;

    @NotNull(message = "Status is required")
    private EmploymentStatus status;

    @NotNull(message = "Department ID is required")
    private Long departmentId; // Only storing department ID in DTO
}
