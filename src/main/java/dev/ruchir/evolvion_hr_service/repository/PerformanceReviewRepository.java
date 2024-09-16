package dev.ruchir.evolvion_hr_service.repository;

import dev.ruchir.evolvion_hr_service.model.core.PerformanceReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PerformanceReviewRepository extends JpaRepository<PerformanceReview, Long> {

    // Find performance review by employeeId and review date
    Optional<PerformanceReview> findByEmployeeIdAndReviewDate(Long employeeId, Date reviewDate);

    // Find all performance reviews for a specific employee
    List<PerformanceReview> findAllByEmployeeId(Long employeeId);

    // Find all performance reviews given by a specific reviewer
    List<PerformanceReview> findAllByReviewerId(Long reviewerId);

    // Find all performance reviews by status (PENDING, COMPLETED, etc.)
    List<PerformanceReview> findAllByStatus(String status);
}
