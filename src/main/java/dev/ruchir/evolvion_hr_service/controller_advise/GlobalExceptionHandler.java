package dev.ruchir.evolvion_hr_service.controller_advise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Handle validation errors (DTO validation)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        logger.error("Validation error: {}", ex.getMessage());

        Map<String, String> validationErrors = new HashMap<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ErrorResponse errorResponse = new ErrorResponse(
                "Validation failed",
                "VALIDATION_ERROR",
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                UUID.randomUUID().toString(),
                validationErrors
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Handle EmployeeNotFoundException
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        logger.error("Employee not found: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "EMPLOYEE_NOT_FOUND",
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                UUID.randomUUID().toString(),
                null
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Handle EmployeeAlreadyExistsException
    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmployeeAlreadyExistsException(EmployeeAlreadyExistsException ex) {
        logger.error("Employee already exists: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "EMPLOYEE_ALREADY_EXISTS",
                LocalDateTime.now(),
                HttpStatus.CONFLICT,
                UUID.randomUUID().toString(),
                null
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    // Handle InvalidEmployeeDataException
    @ExceptionHandler(InvalidEmployeeDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidEmployeeDataException(InvalidEmployeeDataException ex) {
        logger.error("Invalid employee data: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "INVALID_EMPLOYEE_DATA",
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                UUID.randomUUID().toString(),
                null
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Handle DepartmentNotFoundException
    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDepartmentNotFoundException(DepartmentNotFoundException ex) {
        logger.error("Department not found: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "DEPARTMENT_NOT_FOUND",
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                UUID.randomUUID().toString(),
                null
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Handle DepartmentAlreadyExistsException
    @ExceptionHandler(DepartmentAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleDepartmentAlreadyExistsException(DepartmentAlreadyExistsException ex) {
        logger.error("Department already exists: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "DEPARTMENT_ALREADY_EXISTS",
                LocalDateTime.now(),
                HttpStatus.CONFLICT,
                UUID.randomUUID().toString(),
                null
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    // Handle PayrollNotFoundException
    @ExceptionHandler(PayrollNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePayrollNotFoundException(PayrollNotFoundException ex) {
        logger.error("Payroll not found: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "PAYROLL_NOT_FOUND",
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                UUID.randomUUID().toString(),
                null
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Handle PayrollAlreadyExistsException
    @ExceptionHandler(PayrollAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handlePayrollAlreadyExistsException(PayrollAlreadyExistsException ex) {
        logger.error("Payroll already exists: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "PAYROLL_ALREADY_EXISTS",
                LocalDateTime.now(),
                HttpStatus.CONFLICT,
                UUID.randomUUID().toString(),
                null
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    // Handle PerformanceReviewNotFoundException
    @ExceptionHandler(PerformanceReviewNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePerformanceReviewNotFoundException(PerformanceReviewNotFoundException ex) {
        logger.error("Performance review not found: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "PERFORMANCE_REVIEW_NOT_FOUND",
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                UUID.randomUUID().toString(),
                null
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Handle PerformanceReviewAlreadyExistsException
    @ExceptionHandler(PerformanceReviewAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handlePerformanceReviewAlreadyExistsException(PerformanceReviewAlreadyExistsException ex) {
        logger.error("Performance review already exists: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "PERFORMANCE_REVIEW_ALREADY_EXISTS",
                LocalDateTime.now(),
                HttpStatus.CONFLICT,
                UUID.randomUUID().toString(),
                null
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    // Handle AttendanceNotFoundException
    @ExceptionHandler(AttendanceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAttendanceNotFoundException(AttendanceNotFoundException ex) {
        logger.error("Attendance not found: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "ATTENDANCE_NOT_FOUND",
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                UUID.randomUUID().toString(),
                null
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Handle JobPositionNotFoundException
    @ExceptionHandler(JobPositionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleJobPositionNotFoundException(JobPositionNotFoundException ex) {
        logger.error("Job position not found: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "JOB_POSITION_NOT_FOUND",
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                UUID.randomUUID().toString(),
                null
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Handle LeaveRequestNotFoundException
    @ExceptionHandler(LeaveRequestNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLeaveRequestNotFoundException(LeaveRequestNotFoundException ex) {
        logger.error("Leave request not found: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "LEAVE_REQUEST_NOT_FOUND",
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                UUID.randomUUID().toString(),
                null
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Handle LeaveRequestAlreadyProcessedException
    @ExceptionHandler(LeaveRequestAlreadyProcessedException.class)
    public ResponseEntity<ErrorResponse> handleLeaveRequestAlreadyProcessedException(LeaveRequestAlreadyProcessedException ex) {
        logger.error("Leave request already processed: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "LEAVE_REQUEST_ALREADY_PROCESSED",
                LocalDateTime.now(),
                HttpStatus.CONFLICT,
                UUID.randomUUID().toString(),
                null
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    // Handle InvalidLeaveRequestDataException
    @ExceptionHandler(InvalidLeaveRequestDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidLeaveRequestDataException(InvalidLeaveRequestDataException ex) {
        logger.error("Invalid leave request data: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "INVALID_LEAVE_REQUEST_DATA",
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                UUID.randomUUID().toString(),
                null
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    // Handle TrainingProgramNotFoundException
    @ExceptionHandler(TrainingProgramNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTrainingProgramNotFoundException(TrainingProgramNotFoundException ex) {
        logger.error("Training program not found: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "TRAINING_PROGRAM_NOT_FOUND",
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                UUID.randomUUID().toString(),
                null
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Handle TrainingProgramAlreadyExistsException
    @ExceptionHandler(TrainingProgramAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleTrainingProgramAlreadyExistsException(TrainingProgramAlreadyExistsException ex) {
        logger.error("Training program already exists: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "TRAINING_PROGRAM_ALREADY_EXISTS",
                LocalDateTime.now(),
                HttpStatus.CONFLICT,
                UUID.randomUUID().toString(),
                null
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    // Handle InvalidTrainingProgramDataException
    @ExceptionHandler(InvalidTrainingProgramDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTrainingProgramDataException(InvalidTrainingProgramDataException ex) {
        logger.error("Invalid training program data: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "INVALID_TRAINING_PROGRAM_DATA",
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                UUID.randomUUID().toString(),
                null
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Handle TrainingParticipantNotFoundException
    @ExceptionHandler(TrainingParticipantNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTrainingParticipantNotFoundException(TrainingParticipantNotFoundException ex) {
        logger.error("Training participant not found: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "TRAINING_PARTICIPANT_NOT_FOUND",
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                UUID.randomUUID().toString(),
                null
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    // Handle global exceptions (fallback for any unhandled exception)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        logger.error("An unexpected error occurred: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                "An unexpected error occurred.",
                "INTERNAL_SERVER_ERROR",
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                UUID.randomUUID().toString(),
                null
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
