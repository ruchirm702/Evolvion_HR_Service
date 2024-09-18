package dev.ruchir.evolvion_hr_service.controller;

import dev.ruchir.evolvion_hr_service.dto.JobPositionDTO;
import dev.ruchir.evolvion_hr_service.service.interfaces.JobPositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/job-positions")
@RequiredArgsConstructor
@Validated
public class JobPositionController {

    private final JobPositionService jobPositionService;

    // Create a new job position
    @PostMapping
    public ResponseEntity<JobPositionDTO> createJobPosition(@Valid @RequestBody JobPositionDTO jobPositionDTO) {
        JobPositionDTO createdJobPosition = jobPositionService.createJobPosition(jobPositionDTO);
        return ResponseEntity.status(201).body(createdJobPosition);
    }

    // Update an existing job position
    @PutMapping("/{id}")
    public ResponseEntity<JobPositionDTO> updateJobPosition(@PathVariable Long id, @Valid @RequestBody JobPositionDTO jobPositionDTO) {
        JobPositionDTO updatedJobPosition = jobPositionService.updateJobPosition(id, jobPositionDTO);
        return ResponseEntity.ok(updatedJobPosition);
    }

    // Retrieve a job position by ID
    @GetMapping("/{id}")
    public ResponseEntity<JobPositionDTO> getJobPositionById(@PathVariable Long id) {
        JobPositionDTO jobPositionDTO = jobPositionService.getJobPositionById(id);
        return ResponseEntity.ok(jobPositionDTO);
    }

    // Delete a job position by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobPosition(@PathVariable Long id) {
        jobPositionService.deleteJobPosition(id);
        return ResponseEntity.noContent().build();
    }

    // Retrieve all job positions
    @GetMapping
    public ResponseEntity<List<JobPositionDTO>> getAllJobPositions() {
        List<JobPositionDTO> jobPositions = jobPositionService.getAllJobPositions();
        return ResponseEntity.ok(jobPositions);
    }

    // Retrieve job positions by department ID
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<JobPositionDTO>> getJobPositionsByDepartmentId(@PathVariable Long departmentId) {
        List<JobPositionDTO> jobPositions = jobPositionService.getJobPositionsByDepartmentId(departmentId);
        return ResponseEntity.ok(jobPositions);
    }

    // Retrieve job positions within a specified salary range
    @GetMapping("/salary-range")
    public ResponseEntity<List<JobPositionDTO>> getJobPositionsBySalaryRange(
            @RequestParam Double minSalary,
            @RequestParam Double maxSalary
    ) {
        List<JobPositionDTO> jobPositions = jobPositionService.getJobPositionsBySalaryRange(minSalary, maxSalary);
        return ResponseEntity.ok(jobPositions);
    }

    // Search job positions by partial title
    @GetMapping("/search")
    public ResponseEntity<List<JobPositionDTO>> searchJobPositionsByTitle(@RequestParam String title) {
        List<JobPositionDTO> jobPositions = jobPositionService.searchJobPositionsByTitle(title);
        return ResponseEntity.ok(jobPositions);
    }
}
