package dev.ruchir.evolvion_hr_service.controller_advise;

public class PerformanceReviewAlreadyExistsException extends RuntimeException {
    public PerformanceReviewAlreadyExistsException(String message) {
        super(message);
    }
}