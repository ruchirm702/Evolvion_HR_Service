package dev.ruchir.evolvion_hr_service.service.interfaces;

import dev.ruchir.evolvion_hr_service.dto.EmployeeDTO;
import java.util.List;

public interface EmployeeService {

    // Create a new employee
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    // Update an existing employee
    EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO employeeDTO);

    // Get an employee by ID
    EmployeeDTO getEmployeeById(Long employeeId);

    // Delete an employee by ID
    void deleteEmployee(Long employeeId);

    // Get all employees
    List<EmployeeDTO> getAllEmployees();

    // Get employees by department ID
    List<EmployeeDTO> getEmployeesByDepartment(Long departmentId);

    // Get employees by role
    List<EmployeeDTO> getEmployeesByRole(String role);
}
