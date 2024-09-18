package dev.ruchir.evolvion_hr_service.controller_advise;

public class JobPositionNotFoundException extends RuntimeException {
    public JobPositionNotFoundException(String message) {
        super(message);
    }
}
