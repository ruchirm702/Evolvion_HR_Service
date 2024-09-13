package dev.ruchir.evolvion_hr_service.service.interfaces;

import dev.ruchir.evolvion_hr_service.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {

    // Create a new department
    DepartmentDTO createDepartment(DepartmentDTO departmentDTO);

    // Update an existing department
    DepartmentDTO updateDepartment(Long departmentId, DepartmentDTO departmentDTO);

    // Get a department by ID
    DepartmentDTO getDepartmentById(Long departmentId);

    // Delete a department by ID
    void deleteDepartment(Long departmentId);

    // Get all departments
    List<DepartmentDTO> getAllDepartments();

    // Get a department by name
    DepartmentDTO getDepartmentByName(String name);

    // Get all departments that include a specific employee
    List<DepartmentDTO> getDepartmentsByEmployee(Long employeeId);
}
