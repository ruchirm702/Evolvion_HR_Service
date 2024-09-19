package dev.ruchir.evolvion_hr_service.controller_advise;

public class InvalidLeaveRequestDataException extends RuntimeException {

    public InvalidLeaveRequestDataException(String message) {
        super(message);
    }

    public InvalidLeaveRequestDataException(String field, String issue) {
        super("Invalid data for leave request: " + field + " is " + issue);
    }
}
