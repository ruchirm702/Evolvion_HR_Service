package dev.ruchir.evolvion_hr_service.controller_advise;

public class TrainingParticipantNotFoundException extends RuntimeException {
    public TrainingParticipantNotFoundException(String message) {
        super(message);
    }
}