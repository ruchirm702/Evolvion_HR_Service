package dev.ruchir.evolvion_hr_service.controller_advise;

public class LeaveRequestAlreadyProcessedException extends RuntimeException {

    public LeaveRequestAlreadyProcessedException(String message) {
        super(message);
    }

    public LeaveRequestAlreadyProcessedException(Long id) {
        super("Leave request with ID " + id + " has already been processed.");
    }
}