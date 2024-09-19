package dev.ruchir.evolvion_hr_service.controller;

import dev.ruchir.evolvion_hr_service.dto.LeaveRequestDTO;
import dev.ruchir.evolvion_hr_service.model.enums.LeaveRequestStatus;
import dev.ruchir.evolvion_hr_service.service.interfaces.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/leave-requests")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    @Autowired
    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    @PostMapping
    public ResponseEntity<LeaveRequestDTO> createLeaveRequest(@RequestBody LeaveRequestDTO leaveRequestDTO) {
        LeaveRequestDTO createdLeaveRequest = leaveRequestService.createLeaveRequest(leaveRequestDTO);
        return new ResponseEntity<>(createdLeaveRequest, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaveRequestDTO> updateLeaveRequest(@PathVariable Long id, @RequestBody LeaveRequestDTO leaveRequestDTO) {
        LeaveRequestDTO updatedLeaveRequest = leaveRequestService.updateLeaveRequest(id, leaveRequestDTO);
        return new ResponseEntity<>(updatedLeaveRequest, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeaveRequest(@PathVariable Long id) {
        leaveRequestService.deleteLeaveRequest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveRequestDTO> getLeaveRequestById(@PathVariable Long id) {
        Optional<LeaveRequestDTO> leaveRequestDTO = leaveRequestService.getLeaveRequestById(id);
        return leaveRequestDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<LeaveRequestDTO>> getLeaveRequestsByEmployeeId(@PathVariable Long employeeId) {
        List<LeaveRequestDTO> leaveRequests = leaveRequestService.getLeaveRequestsByEmployeeId(employeeId);
        return new ResponseEntity<>(leaveRequests, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<LeaveRequestDTO>> getLeaveRequestsByStatus(@PathVariable LeaveRequestStatus status) {
        List<LeaveRequestDTO> leaveRequests = leaveRequestService.getLeaveRequestsByStatus(status);
        return new ResponseEntity<>(leaveRequests, HttpStatus.OK);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<LeaveRequestDTO>> getLeaveRequestsWithinDateRange(
            @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<LeaveRequestDTO> leaveRequests = leaveRequestService.getLeaveRequestsWithinDateRange(startDate, endDate);
        return new ResponseEntity<>(leaveRequests, HttpStatus.OK);
    }

    @GetMapping("/employee/{employeeId}/date-range")
    public ResponseEntity<List<LeaveRequestDTO>> getLeaveRequestsByEmployeeIdAndDateRange(
            @PathVariable Long employeeId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        List<LeaveRequestDTO> leaveRequests = leaveRequestService.getLeaveRequestsByEmployeeIdAndDateRange(employeeId, startDate, endDate);
        return new ResponseEntity<>(leaveRequests, HttpStatus.OK);
    }

    @GetMapping("/count/status/{status}/employee/{employeeId}")
    public ResponseEntity<Long> countLeaveRequestsByStatusAndEmployeeId(
            @PathVariable LeaveRequestStatus status,
            @PathVariable Long employeeId) {
        Long count = leaveRequestService.countLeaveRequestsByStatusAndEmployeeId(status, employeeId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/exists/pending")
    public ResponseEntity<Boolean> isPendingLeaveRequestExists(
            @RequestParam Long employeeId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        boolean exists = leaveRequestService.isPendingLeaveRequestExists(employeeId, LeaveRequestStatus.PENDING, startDate, endDate);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }
}
