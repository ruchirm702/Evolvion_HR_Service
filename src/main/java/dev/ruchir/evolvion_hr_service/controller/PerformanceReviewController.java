package dev.ruchir.evolvion_hr_service.controller;

import dev.ruchir.evolvion_hr_service.dto.PerformanceReviewDTO;
import dev.ruchir.evolvion_hr_service.service.interfaces.PerformanceReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/performance-reviews")
@RequiredArgsConstructor
public class PerformanceReviewController {

    private final PerformanceReviewService performanceReviewService;

    // Create a new performance review
    @PostMapping
    public ResponseEntity<PerformanceReviewDTO> createPerformanceReview(@Valid @RequestBody PerformanceReviewDTO performanceReviewDTO) {
        PerformanceReviewDTO createdReview = performanceReviewService.createPerformanceReview(performanceReviewDTO);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    // Update an existing performance review by ID
    @PutMapping("/{reviewId}")
    public ResponseEntity<PerformanceReviewDTO> updatePerformanceReview(
            @PathVariable Long reviewId,
            @Valid @RequestBody PerformanceReviewDTO performanceReviewDTO) {
        PerformanceReviewDTO updatedReview = performanceReviewService.updatePerformanceReview(reviewId, performanceReviewDTO);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    // Get a performance review by ID
    @GetMapping("/{reviewId}")
    public ResponseEntity<PerformanceReviewDTO> getPerformanceReviewById(@PathVariable Long reviewId) {
        PerformanceReviewDTO review = performanceReviewService.getPerformanceReviewById(reviewId);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    // Delete a performance review by ID
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deletePerformanceReview(@PathVariable Long reviewId) {
        performanceReviewService.deletePerformanceReview(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get all performance reviews for a specific employee
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<PerformanceReviewDTO>> getPerformanceReviewsByEmployee(@PathVariable Long employeeId) {
        List<PerformanceReviewDTO> reviews = performanceReviewService.getPerformanceReviewsByEmployee(employeeId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    // Get all performance reviews conducted by a specific reviewer
    @GetMapping("/reviewer/{reviewerId}")
    public ResponseEntity<List<PerformanceReviewDTO>> getPerformanceReviewsByReviewer(@PathVariable Long reviewerId) {
        List<PerformanceReviewDTO> reviews = performanceReviewService.getPerformanceReviewsByReviewer(reviewerId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    // Get all performance reviews by status (e.g., PENDING, COMPLETED)
    @GetMapping("/status/{status}")
    public ResponseEntity<List<PerformanceReviewDTO>> getPerformanceReviewsByStatus(@PathVariable String status) {
        List<PerformanceReviewDTO> reviews = performanceReviewService.getPerformanceReviewsByStatus(status);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    // Calculate the average performance score for a specific employee
    @GetMapping("/employee/{employeeId}/average-score")
    public ResponseEntity<Double> calculateAverageScore(@PathVariable Long employeeId) {
        double averageScore = performanceReviewService.calculateAverageScore(employeeId);
        return new ResponseEntity<>(averageScore, HttpStatus.OK);
    }
}
