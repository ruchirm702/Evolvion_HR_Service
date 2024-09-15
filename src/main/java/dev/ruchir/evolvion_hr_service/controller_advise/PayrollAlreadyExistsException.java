package dev.ruchir.evolvion_hr_service.controller_advise;

public class PayrollAlreadyExistsException extends RuntimeException {
    public PayrollAlreadyExistsException(String message) {
        super(message);
    }
}