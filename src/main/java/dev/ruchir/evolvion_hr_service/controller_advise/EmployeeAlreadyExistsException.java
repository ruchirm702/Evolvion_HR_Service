package dev.ruchir.evolvion_hr_service.controller_advise;

public class EmployeeAlreadyExistsException extends CustomException {
    public EmployeeAlreadyExistsException(String email) {
        super("Employee with email " + email + " already exists.");
    }
}