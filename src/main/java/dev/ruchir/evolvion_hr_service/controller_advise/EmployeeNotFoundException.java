package dev.ruchir.evolvion_hr_service.controller_advise;

public class EmployeeNotFoundException extends CustomException {
    public EmployeeNotFoundException(Long employeeId) {
        super("Employee with ID " + employeeId + " not found.");
    }
}