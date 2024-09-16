package dev.ruchir.evolvion_hr_service.service.impl;

import dev.ruchir.evolvion_hr_service.controller_advise.PerformanceReviewAlreadyExistsException;
import dev.ruchir.evolvion_hr_service.controller_advise.PerformanceReviewNotFoundException;
import dev.ruchir.evolvion_hr_service.dto.PerformanceReviewDTO;
import dev.ruchir.evolvion_hr_service.mappers.PerformanceReviewMapper;
import dev.ruchir.evolvion_hr_service.model.core.PerformanceReview;
import dev.ruchir.evolvion_hr_service.repository.PerformanceReviewRepository;
import dev.ruchir.evolvion_hr_service.service.interfaces.PerformanceReviewService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerformanceReviewServiceImpl implements PerformanceReviewService {

    private static final Logger logger = LoggerFactory.getLogger(PerformanceReviewServiceImpl.class);

    private final PerformanceReviewRepository performanceReviewRepository;
    private final PerformanceReviewMapper performanceReviewMapper;

    @Override
    public PerformanceReviewDTO createPerformanceReview(PerformanceReviewDTO performanceReviewDTO) {
        logger.info("Creating performance review for employee ID: {}", performanceReviewDTO.getEmployeeId());

        Optional<PerformanceReview> existingReview = performanceReviewRepository.findByEmployeeIdAndReviewDate(
                performanceReviewDTO.getEmployeeId(), performanceReviewDTO.getReviewDate()
        );
        if (existingReview.isPresent()) {
            logger.error("Performance review already exists for employee ID {} on date {}",
                    performanceReviewDTO.getEmployeeId(), performanceReviewDTO.getReviewDate());
            throw new PerformanceReviewAlreadyExistsException(
                    "Performance review already exists for employee ID " + performanceReviewDTO.getEmployeeId() + " on " + performanceReviewDTO.getReviewDate()
            );
        }

        PerformanceReview performanceReview = performanceReviewMapper.toEntity(performanceReviewDTO);
        PerformanceReview savedReview = performanceReviewRepository.save(performanceReview);
        logger.info("Performance review created successfully with ID: {}", savedReview.getId());

        return performanceReviewMapper.toDTO(savedReview);
    }

    @Override
    public PerformanceReviewDTO updatePerformanceReview(Long reviewId, PerformanceReviewDTO performanceReviewDTO) {
        logger.info("Updating performance review with ID: {}", reviewId);

        PerformanceReview existingReview = performanceReviewRepository.findById(reviewId)
                .orElseThrow(() -> {
                    logger.error("Performance review with ID {} not found", reviewId);
                    return new PerformanceReviewNotFoundException("Performance review with ID " + reviewId + " not found");
                });

        existingReview.setScore(performanceReviewDTO.getScore());
        existingReview.setComments(performanceReviewDTO.getComments());
        existingReview.setReviewDate(performanceReviewDTO.getReviewDate());
        existingReview.setStatus(performanceReviewDTO.getStatus().name());

        PerformanceReview updatedReview = performanceReviewRepository.save(existingReview);
        logger.info("Performance review updated successfully with ID: {}", updatedReview.getId());

        return performanceReviewMapper.toDTO(updatedReview);
    }

    @Override
    public PerformanceReviewDTO getPerformanceReviewById(Long reviewId) {
        logger.info("Fetching performance review with ID: {}", reviewId);

        PerformanceReview performanceReview = performanceReviewRepository.findById(reviewId)
                .orElseThrow(() -> {
                    logger.error("Performance review with ID {} not found", reviewId);
                    return new PerformanceReviewNotFoundException("Performance review with ID " + reviewId + " not found");
                });

        return performanceReviewMapper.toDTO(performanceReview);
    }

    @Override
    public void deletePerformanceReview(Long reviewId) {
        logger.info("Deleting performance review with ID: {}", reviewId);

        PerformanceReview performanceReview = performanceReviewRepository.findById(reviewId)
                .orElseThrow(() -> {
                    logger.error("Performance review with ID {} not found", reviewId);
                    return new PerformanceReviewNotFoundException("Performance review with ID " + reviewId + " not found");
                });

        performanceReviewRepository.delete(performanceReview);
        logger.info("Performance review deleted successfully with ID: {}", reviewId);
    }

    @Override
    public List<PerformanceReviewDTO> getPerformanceReviewsByEmployee(Long employeeId) {
        logger.info("Fetching performance reviews for employee ID: {}", employeeId);

        List<PerformanceReview> performanceReviews = performanceReviewRepository.findAllByEmployeeId(employeeId);
        return performanceReviews.stream()
                .map(performanceReviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PerformanceReviewDTO> getPerformanceReviewsByReviewer(Long reviewerId) {
        logger.info("Fetching performance reviews by reviewer ID: {}", reviewerId);

        List<PerformanceReview> performanceReviews = performanceReviewRepository.findAllByReviewerId(reviewerId);
        return performanceReviews.stream()
                .map(performanceReviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PerformanceReviewDTO> getPerformanceReviewsByStatus(String status) {
        logger.info("Fetching performance reviews with status: {}", status);

        List<PerformanceReview> performanceReviews = performanceReviewRepository.findAllByStatus(status);
        return performanceReviews.stream()
                .map(performanceReviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public double calculateAverageScore(Long employeeId) {
        logger.info("Calculating average score for employee ID: {}", employeeId);

        List<PerformanceReview> performanceReviews = performanceReviewRepository.findAllByEmployeeId(employeeId);
        return performanceReviews.stream()
                .mapToDouble(PerformanceReview::getScore)
                .average()
                .orElse(0.0);
    }
}
