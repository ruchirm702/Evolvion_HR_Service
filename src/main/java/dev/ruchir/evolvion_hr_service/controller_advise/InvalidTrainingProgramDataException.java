package dev.ruchir.evolvion_hr_service.controller_advise;

public class InvalidTrainingProgramDataException extends RuntimeException {
    public InvalidTrainingProgramDataException(String message) {
        super(message);
    }
}