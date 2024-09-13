package dev.ruchir.evolvion_hr_service.controller;

import dev.ruchir.evolvion_hr_service.dto.EmployeeDTO;
import dev.ruchir.evolvion_hr_service.service.interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // Create a new employee
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
        return ResponseEntity.status(201).body(createdEmployee);
    }

    // Update an existing employee
    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @PathVariable Long employeeId,
            @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(employeeId, employeeDTO);
        return ResponseEntity.ok(updatedEmployee);
    }

    // Get an employee by ID
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDTO);
    }

    // Delete an employee by ID
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }

    // Get all employees
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    // Get employees by department ID
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByDepartment(@PathVariable Long departmentId) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByDepartment(departmentId);
        return ResponseEntity.ok(employees);
    }

    // Get employees by role
    @GetMapping("/role/{role}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByRole(@PathVariable String role) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByRole(role);
        return ResponseEntity.ok(employees);
    }
}
