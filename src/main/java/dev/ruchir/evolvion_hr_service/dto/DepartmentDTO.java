package dev.ruchir.evolvion_hr_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DepartmentDTO {
    private Long id;
    private String name;
    private List<EmployeeDTO> employees;
}
