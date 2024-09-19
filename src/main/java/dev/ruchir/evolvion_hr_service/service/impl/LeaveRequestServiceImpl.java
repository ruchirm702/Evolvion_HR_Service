package dev.ruchir.evolvion_hr_service.service.impl;

import dev.ruchir.evolvion_hr_service.dto.LeaveRequestDTO;
import dev.ruchir.evolvion_hr_service.mappers.LeaveRequestMapper;
import dev.ruchir.evolvion_hr_service.model.core.LeaveRequest;
import dev.ruchir.evolvion_hr_service.model.enums.LeaveRequestStatus;
import dev.ruchir.evolvion_hr_service.repository.LeaveRequestRepository;
import dev.ruchir.evolvion_hr_service.service.interfaces.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final LeaveRequestMapper leaveRequestMapper;

    @Autowired
    public LeaveRequestServiceImpl(LeaveRequestRepository leaveRequestRepository, LeaveRequestMapper leaveRequestMapper) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.leaveRequestMapper = leaveRequestMapper;
    }

    @Override
    public LeaveRequestDTO createLeaveRequest(LeaveRequestDTO leaveRequestDTO) {
        LeaveRequest leaveRequest = leaveRequestMapper.toEntity(leaveRequestDTO);
        leaveRequest.setStatus(LeaveRequestStatus.PENDING); // Use enum directly
        LeaveRequest savedLeaveRequest = leaveRequestRepository.save(leaveRequest);
        return leaveRequestMapper.toDTO(savedLeaveRequest);
    }

    @Override
    public LeaveRequestDTO updateLeaveRequest(Long id, LeaveRequestDTO leaveRequestDTO) {
        Optional<LeaveRequest> existingLeaveRequestOpt = leaveRequestRepository.findById(id);
        if (existingLeaveRequestOpt.isPresent()) {
            LeaveRequest existingLeaveRequest = existingLeaveRequestOpt.get();
            LeaveRequest updatedLeaveRequest = leaveRequestMapper.toEntity(leaveRequestDTO);
            updatedLeaveRequest.setId(id); // Ensure the ID is maintained
            updatedLeaveRequest.setEmployee(existingLeaveRequest.getEmployee()); // Retain existing employee
            LeaveRequest savedLeaveRequest = leaveRequestRepository.save(updatedLeaveRequest);
            return leaveRequestMapper.toDTO(savedLeaveRequest);
        } else {
            throw new RuntimeException("Leave request not found"); // Replace with a custom exception if needed
        }
    }

    @Override
    public void deleteLeaveRequest(Long id) {
        if (leaveRequestRepository.existsById(id)) {
            leaveRequestRepository.deleteById(id);
        } else {
            throw new RuntimeException("Leave request not found"); // Replace with a custom exception if needed
        }
    }

    @Override
    public Optional<LeaveRequestDTO> getLeaveRequestById(Long id) {
        return leaveRequestRepository.findById(id).map(leaveRequestMapper::toDTO);
    }

    @Override
    public List<LeaveRequestDTO> getLeaveRequestsByEmployeeId(Long employeeId) {
        List<LeaveRequest> leaveRequests = leaveRequestRepository.findByEmployeeId(employeeId);
        return leaveRequestMapper.toDTOs(leaveRequests);
    }

    @Override
    public List<LeaveRequestDTO> getLeaveRequestsByStatus(LeaveRequestStatus status) {
        List<LeaveRequest> leaveRequests = leaveRequestRepository.findByStatus(status);
        return leaveRequestMapper.toDTOs(leaveRequests);
    }

    @Override
    public List<LeaveRequestDTO> getLeaveRequestsWithinDateRange(LocalDate startDate, LocalDate endDate) {
        List<LeaveRequest> leaveRequests = leaveRequestRepository.findByStartDateBetween(startDate, endDate);
        return leaveRequestMapper.toDTOs(leaveRequests);
    }

    @Override
    public List<LeaveRequestDTO> getLeaveRequestsByEmployeeIdAndDateRange(Long employeeId, LocalDate startDate, LocalDate endDate) {
        List<LeaveRequest> leaveRequests = leaveRequestRepository.findByEmployeeIdAndStartDateBetween(employeeId, startDate, endDate);
        return leaveRequestMapper.toDTOs(leaveRequests);
    }

    @Override
    public Long countLeaveRequestsByStatusAndEmployeeId(LeaveRequestStatus status, Long employeeId) {
        return leaveRequestRepository.countByStatusAndEmployeeId(status, employeeId);
    }

    @Override
    public boolean isPendingLeaveRequestExists(Long employeeId, LeaveRequestStatus status, LocalDate startDate, LocalDate endDate) {
        return leaveRequestRepository.existsByEmployeeIdAndStatusAndStartDateBetween(employeeId, status, startDate, endDate);
    }
}
