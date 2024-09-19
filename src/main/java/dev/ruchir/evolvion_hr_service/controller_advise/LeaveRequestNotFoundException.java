package dev.ruchir.evolvion_hr_service.controller_advise;

public class LeaveRequestNotFoundException extends RuntimeException {

    public LeaveRequestNotFoundException(String message) {
        super(message);
    }

    public LeaveRequestNotFoundException(Long id) {
        super("Leave request with ID " + id + " not found.");
    }
}