package dev.ruchir.evolvion_hr_service.controller_advise;

public class InvalidEmployeeDataException extends CustomException {
    public InvalidEmployeeDataException(String message) {
        super("Invalid employee data: " + message);
    }
}