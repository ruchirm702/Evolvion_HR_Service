package dev.ruchir.evolvion_hr_service.service.interfaces;

import dev.ruchir.evolvion_hr_service.dto.LeaveRequestDTO;
import dev.ruchir.evolvion_hr_service.model.enums.LeaveRequestStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LeaveRequestService {

    LeaveRequestDTO createLeaveRequest(LeaveRequestDTO leaveRequestDTO);
    LeaveRequestDTO updateLeaveRequest(Long id, LeaveRequestDTO leaveRequestDTO);
    void deleteLeaveRequest(Long id);
    Optional<LeaveRequestDTO> getLeaveRequestById(Long id);
    List<LeaveRequestDTO> getLeaveRequestsByEmployeeId(Long employeeId);
    List<LeaveRequestDTO> getLeaveRequestsByStatus(LeaveRequestStatus status);
    List<LeaveRequestDTO> getLeaveRequestsWithinDateRange(LocalDate startDate, LocalDate endDate);
    List<LeaveRequestDTO> getLeaveRequestsByEmployeeIdAndDateRange(Long employeeId, LocalDate startDate, LocalDate endDate);
    Long countLeaveRequestsByStatusAndEmployeeId(LeaveRequestStatus status, Long employeeId);
    boolean isPendingLeaveRequestExists(Long employeeId, LeaveRequestStatus status, LocalDate startDate, LocalDate endDate);
}
