package dev.ruchir.evolvion_hr_service.repository;

import dev.ruchir.evolvion_hr_service.model.core.LeaveRequest;
import dev.ruchir.evolvion_hr_service.model.enums.LeaveRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface
LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    // Find leave request by ID
    Optional<LeaveRequest> findById(Long id);

    // Find all leave requests by employee ID
    List<LeaveRequest> findByEmployeeId(Long employeeId);

    // Find leave requests by status
    List<LeaveRequest> findByStatus(LeaveRequestStatus status);

    // Find leave requests within a date range
    List<LeaveRequest> findByStartDateBetween(LocalDate startDate, LocalDate endDate);

    // Find leave requests for an employee in a date range
    List<LeaveRequest> findByEmployeeIdAndStartDateBetween(Long employeeId, LocalDate startDate, LocalDate endDate);

    // Count leave requests by status and employee ID
    Long countByStatusAndEmployeeId(LeaveRequestStatus status, Long employeeId);

    // Check if an employee has a pending leave request within a date range
    boolean existsByEmployeeIdAndStatusAndStartDateBetween(Long employeeId, LeaveRequestStatus status, LocalDate startDate, LocalDate endDate);
}
