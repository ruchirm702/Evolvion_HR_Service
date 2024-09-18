package dev.ruchir.evolvion_hr_service.service.interfaces;

import dev.ruchir.evolvion_hr_service.dto.JobPositionDTO;

import java.util.List;

public interface JobPositionService {

    // Create a new job position
    JobPositionDTO createJobPosition(JobPositionDTO jobPositionDTO);

    // Update an existing job position
    JobPositionDTO updateJobPosition(Long id, JobPositionDTO jobPositionDTO);

    // Retrieve a job position by ID
    JobPositionDTO getJobPositionById(Long id);

    // Delete a job position by ID
    void deleteJobPosition(Long id);

    // Retrieve all job positions
    List<JobPositionDTO> getAllJobPositions();

    // Retrieve job positions by department ID
    List<JobPositionDTO> getJobPositionsByDepartmentId(Long departmentId);

    // Retrieve job positions within a specified salary range
    List<JobPositionDTO> getJobPositionsBySalaryRange(Double minSalary, Double maxSalary);

    // Search job positions by partial title
    List<JobPositionDTO> searchJobPositionsByTitle(String partialTitle);
}
