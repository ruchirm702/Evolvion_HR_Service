package dev.ruchir.evolvion_hr_service.controller_advise;

public class TrainingProgramAlreadyExistsException extends RuntimeException {
    public TrainingProgramAlreadyExistsException(String message) {
        super(message);
    }
}