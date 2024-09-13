package dev.ruchir.evolvion_hr_service.controller;

import dev.ruchir.evolvion_hr_service.dto.DepartmentDTO;
import dev.ruchir.evolvion_hr_service.service.interfaces.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    // Create a new department
    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO createdDepartment = departmentService.createDepartment(departmentDTO);
        return ResponseEntity.status(201).body(createdDepartment);
    }

    // Update an existing department
    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> updateDepartment(
            @PathVariable Long departmentId,
            @Valid @RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO updatedDepartment = departmentService.updateDepartment(departmentId, departmentDTO);
        return ResponseEntity.ok(updatedDepartment);
    }

    // Get a department by ID
    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long departmentId) {
        DepartmentDTO departmentDTO = departmentService.getDepartmentById(departmentId);
        return ResponseEntity.ok(departmentDTO);
    }

    // Delete a department by ID
    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long departmentId) {
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.noContent().build();
    }

    // Get all departments
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<DepartmentDTO> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    // Get a department by name
    @GetMapping("/name/{name}")
    public ResponseEntity<DepartmentDTO> getDepartmentByName(@PathVariable String name) {
        DepartmentDTO departmentDTO = departmentService.getDepartmentByName(name);
        return ResponseEntity.ok(departmentDTO);
    }

    // Get departments by employee ID
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<DepartmentDTO>> getDepartmentsByEmployee(@PathVariable Long employeeId) {
        List<DepartmentDTO> departments = departmentService.getDepartmentsByEmployee(employeeId);
        return ResponseEntity.ok(departments);
    }
}
