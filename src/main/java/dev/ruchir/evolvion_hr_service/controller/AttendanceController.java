package dev.ruchir.evolvion_hr_service.controller;

import dev.ruchir.evolvion_hr_service.dto.AttendanceDTO;
import dev.ruchir.evolvion_hr_service.service.interfaces.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendances")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<AttendanceDTO> createAttendance(@RequestBody AttendanceDTO attendanceDTO) {
        AttendanceDTO createdAttendance = attendanceService.createAttendance(attendanceDTO);
        return new ResponseEntity<>(createdAttendance, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttendanceDTO> updateAttendance(@PathVariable("id") Long id, @RequestBody AttendanceDTO attendanceDTO) {
        AttendanceDTO updatedAttendance = attendanceService.updateAttendance(id, attendanceDTO);
        return new ResponseEntity<>(updatedAttendance, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceDTO> getAttendanceById(@PathVariable("id") Long id) {
        AttendanceDTO attendanceDTO = attendanceService.getAttendanceById(id);
        return new ResponseEntity<>(attendanceDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable("id") Long id) {
        attendanceService.deleteAttendance(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceByEmployeeId(@PathVariable("employeeId") Long employeeId) {
        List<AttendanceDTO> attendances = attendanceService.getAttendanceByEmployeeId(employeeId);
        return new ResponseEntity<>(attendances, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AttendanceDTO>> getAllAttendances() {
        List<AttendanceDTO> attendances = attendanceService.getAllAttendances();
        return new ResponseEntity<>(attendances, HttpStatus.OK);
    }

    @GetMapping("/hours/{employeeId}")
    public ResponseEntity<Double> calculateHoursWorked(@PathVariable("employeeId") Long employeeId) {
        double hoursWorked = attendanceService.calculateHoursWorked(employeeId);
        return new ResponseEntity<>(hoursWorked, HttpStatus.OK);
    }
}
