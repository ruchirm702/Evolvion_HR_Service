package dev.ruchir.evolvion_hr_service.controller_advise;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
