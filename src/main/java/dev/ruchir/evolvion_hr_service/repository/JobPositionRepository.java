package dev.ruchir.evolvion_hr_service.repository;

import dev.ruchir.evolvion_hr_service.model.core.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobPositionRepository extends JpaRepository<JobPosition, Long> {

    // Find job position by title (to check for duplicates or retrieve specific job position)
    Optional<JobPosition> findByTitle(String title);

    // Find job positions by department ID
    List<JobPosition> findByDepartmentId(Long departmentId);

    // Find job positions where salary range falls between minimum and maximum values
    List<JobPosition> findBySalaryRangeMinGreaterThanEqualAndSalaryRangeMaxLessThanEqual(Double minSalary, Double maxSalary);

    // Find all job positions with a specific department
    List<JobPosition> findAllByDepartmentId(Long departmentId);

    // Find job positions by partial title match (case-insensitive, for search functionality)
    List<JobPosition> findByTitleContainingIgnoreCase(String partialTitle);
}
