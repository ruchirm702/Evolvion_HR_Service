package dev.ruchir.evolvion_hr_service.controller_advise;

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

    // Handle validation errors (DTO validation)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> validationErrors = new HashMap<>();

        // Collect field errors
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        // Create an ErrorResponse with validation errors
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

    // Handle global exceptions (fallback for any unhandled exception)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
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
