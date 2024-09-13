package dev.ruchir.evolvion_hr_service.controller_advise;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(String message) {
        super(message);
    }
}
