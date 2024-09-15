package dev.ruchir.evolvion_hr_service.controller_advise;

public class PayrollNotFoundException extends RuntimeException {
    public PayrollNotFoundException(String message) {
        super(message);
    }
}
