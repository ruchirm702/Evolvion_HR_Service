package dev.ruchir.evolvion_hr_service.service.interfaces;

import dev.ruchir.evolvion_hr_service.dto.PerformanceReviewDTO;

import java.util.List;

public interface PerformanceReviewService {

    // Create a new performance review
    PerformanceReviewDTO createPerformanceReview(PerformanceReviewDTO performanceReviewDTO);

    // Update an existing performance review
    PerformanceReviewDTO updatePerformanceReview(Long reviewId, PerformanceReviewDTO performanceReviewDTO);

    // Get a performance review by ID
    PerformanceReviewDTO getPerformanceReviewById(Long reviewId);

    // Delete a performance review by ID
    void deletePerformanceReview(Long reviewId);

    // Get all performance reviews for a specific employee
    List<PerformanceReviewDTO> getPerformanceReviewsByEmployee(Long employeeId);

    // Get all performance reviews conducted by a specific reviewer
    List<PerformanceReviewDTO> getPerformanceReviewsByReviewer(Long reviewerId);

    // Get all performance reviews by status (PENDING, COMPLETED, etc.)
    List<PerformanceReviewDTO> getPerformanceReviewsByStatus(String status);

    // Calculate performance score (e.g., average score)
    double calculateAverageScore(Long employeeId);
}
