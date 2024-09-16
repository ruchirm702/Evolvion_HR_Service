package dev.ruchir.evolvion_hr_service.controller;

import dev.ruchir.evolvion_hr_service.dto.AttendanceDTO;
import dev.ruchir.evolvion_hr_service.service.interfaces.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/attendances")
@RequiredArgsConstructor
@Validated
public class AttendanceController {

    private static final Logger logger = LoggerFactory.getLogger(AttendanceController.class);
    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<AttendanceDTO> createAttendance(@Valid @RequestBody AttendanceDTO attendanceDTO) {
        logger.info("Creating attendance record: {}", attendanceDTO);
        AttendanceDTO createdAttendance = attendanceService.createAttendance(attendanceDTO);
        logger.info("Attendance record created successfully: {}", createdAttendance);
        return new ResponseEntity<>(createdAttendance, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttendanceDTO> updateAttendance(@PathVariable("id") Long id, @Valid @RequestBody AttendanceDTO attendanceDTO) {
        logger.info("Updating attendance record with id {}: {}", id, attendanceDTO);
        AttendanceDTO updatedAttendance = attendanceService.updateAttendance(id, attendanceDTO);
        logger.info("Attendance record updated successfully: {}", updatedAttendance);
        return new ResponseEntity<>(updatedAttendance, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceDTO> getAttendanceById(@PathVariable("id") Long id) {
        logger.info("Retrieving attendance record with id {}", id);
        AttendanceDTO attendanceDTO = attendanceService.getAttendanceById(id);
        logger.info("Attendance record retrieved successfully: {}", attendanceDTO);
        return new ResponseEntity<>(attendanceDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable("id") Long id) {
        logger.info("Deleting attendance record with id {}", id);
        attendanceService.deleteAttendance(id);
        logger.info("Attendance record deleted successfully with id {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceByEmployeeId(@PathVariable("employeeId") Long employeeId) {
        logger.info("Retrieving attendance records for employee id {}", employeeId);
        List<AttendanceDTO> attendances = attendanceService.getAttendanceByEmployeeId(employeeId);
        logger.info("Retrieved {} attendance records for employee id {}", attendances.size(), employeeId);
        return new ResponseEntity<>(attendances, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AttendanceDTO>> getAllAttendances() {
        logger.info("Retrieving all attendance records");
        List<AttendanceDTO> attendances = attendanceService.getAllAttendances();
        logger.info("Retrieved {} attendance records", attendances.size());
        return new ResponseEntity<>(attendances, HttpStatus.OK);
    }

    @GetMapping("/hours/{employeeId}")
    public ResponseEntity<Double> calculateHoursWorked(@PathVariable("employeeId") Long employeeId) {
        logger.info("Calculating hours worked for employee id {}", employeeId);
        double hoursWorked = attendanceService.calculateHoursWorked(employeeId);
        logger.info("Total hours worked for employee id {}: {}", employeeId, hoursWorked);
        return new ResponseEntity<>(hoursWorked, HttpStatus.OK);
    }
}
