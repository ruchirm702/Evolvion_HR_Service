package dev.ruchir.evolvion_hr_service.service.interfaces;

import dev.ruchir.evolvion_hr_service.dto.AttendanceDTO;

import java.util.List;

public interface AttendanceService {

    AttendanceDTO createAttendance(AttendanceDTO attendanceDTO);
    AttendanceDTO updateAttendance(Long id, AttendanceDTO attendanceDTO);
    AttendanceDTO getAttendanceById(Long id);
    void deleteAttendance(Long id);
    List<AttendanceDTO> getAttendancesByEmployee(Long employeeId);
    List<AttendanceDTO> getAllAttendances();
    double calculateHoursWorked(Long employeeId);

}
