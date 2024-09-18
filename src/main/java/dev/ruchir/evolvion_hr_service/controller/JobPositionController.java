package dev.ruchir.evolvion_hr_service.controller;

import dev.ruchir.evolvion_hr_service.dto.JobPositionDTO;
import dev.ruchir.evolvion_hr_service.service.interfaces.JobPositionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(JobPositionController.class);

    private final JobPositionService jobPositionService;

    // Create a new job position
    @PostMapping
    public ResponseEntity<JobPositionDTO> createJobPosition(@Valid @RequestBody JobPositionDTO jobPositionDTO) {
        logger.info("Creating job position: {}", jobPositionDTO);
        JobPositionDTO createdJobPosition = jobPositionService.createJobPosition(jobPositionDTO);
        logger.info("Job position created with ID: {}", createdJobPosition.getId());
        return ResponseEntity.status(201).body(createdJobPosition);
    }

    // Update an existing job position
    @PutMapping("/{id}")
    public ResponseEntity<JobPositionDTO> updateJobPosition(@PathVariable Long id, @Valid @RequestBody JobPositionDTO jobPositionDTO) {
        logger.info("Updating job position with ID: {}", id);
        JobPositionDTO updatedJobPosition = jobPositionService.updateJobPosition(id, jobPositionDTO);
        logger.info("Job position updated with ID: {}", updatedJobPosition.getId());
        return ResponseEntity.ok(updatedJobPosition);
    }

    // Retrieve a job position by ID
    @GetMapping("/{id}")
    public ResponseEntity<JobPositionDTO> getJobPositionById(@PathVariable Long id) {
        logger.info("Retrieving job position with ID: {}", id);
        JobPositionDTO jobPositionDTO = jobPositionService.getJobPositionById(id);
        return ResponseEntity.ok(jobPositionDTO);
    }

    // Delete a job position by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobPosition(@PathVariable Long id) {
        logger.info("Deleting job position with ID: {}", id);
        jobPositionService.deleteJobPosition(id);
        return ResponseEntity.noContent().build();
    }

    // Retrieve all job positions
    @GetMapping
    public ResponseEntity<List<JobPositionDTO>> getAllJobPositions() {
        logger.info("Retrieving all job positions");
        List<JobPositionDTO> jobPositions = jobPositionService.getAllJobPositions();
        return ResponseEntity.ok(jobPositions);
    }

    // Retrieve job positions by department ID
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<JobPositionDTO>> getJobPositionsByDepartmentId(@PathVariable Long departmentId) {
        logger.info("Retrieving job positions for department ID: {}", departmentId);
        List<JobPositionDTO> jobPositions = jobPositionService.getJobPositionsByDepartmentId(departmentId);
        return ResponseEntity.ok(jobPositions);
    }

    // Retrieve job positions within a specified salary range
    @GetMapping("/salary-range")
    public ResponseEntity<List<JobPositionDTO>> getJobPositionsBySalaryRange(
            @RequestParam Double minSalary,
            @RequestParam Double maxSalary
    ) {
        logger.info("Retrieving job positions with salary range: {} - {}", minSalary, maxSalary);
        List<JobPositionDTO> jobPositions = jobPositionService.getJobPositionsBySalaryRange(minSalary, maxSalary);
        return ResponseEntity.ok(jobPositions);
    }

    // Search job positions by partial title
    @GetMapping("/search")
    public ResponseEntity<List<JobPositionDTO>> searchJobPositionsByTitle(@RequestParam String title) {
        logger.info("Searching job positions with title containing: {}", title);
        List<JobPositionDTO> jobPositions = jobPositionService.searchJobPositionsByTitle(title);
        return ResponseEntity.ok(jobPositions);
    }
}
