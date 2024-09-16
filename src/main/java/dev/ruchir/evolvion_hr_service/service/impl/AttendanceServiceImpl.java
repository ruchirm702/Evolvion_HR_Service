package dev.ruchir.evolvion_hr_service.service.impl;

import dev.ruchir.evolvion_hr_service.controller_advise.AttendanceNotFoundException;
import dev.ruchir.evolvion_hr_service.dto.AttendanceDTO;
import dev.ruchir.evolvion_hr_service.mappers.AttendanceMapper;
import dev.ruchir.evolvion_hr_service.model.core.Attendance;
import dev.ruchir.evolvion_hr_service.repository.AttendanceRepository;
import dev.ruchir.evolvion_hr_service.service.interfaces.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;

    @Override
    public AttendanceDTO createAttendance(AttendanceDTO attendanceDTO) {
        Attendance attendance = attendanceMapper.toEntity(attendanceDTO);
        attendance = attendanceRepository.save(attendance);
        return attendanceMapper.toDTO(attendance);
    }

    @Override
    public AttendanceDTO updateAttendance(Long id, AttendanceDTO attendanceDTO) {
        Attendance existingAttendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new AttendanceNotFoundException("Attendance not found with id: " + id));

        existingAttendance.setCheckInTime(attendanceDTO.getCheckInTime());
        existingAttendance.setCheckOutTime(attendanceDTO.getCheckOutTime());
        existingAttendance.setHoursWorked(attendanceDTO.getHoursWorked());

        attendanceRepository.save(existingAttendance);
        return attendanceMapper.toDTO(existingAttendance);
    }

    @Override
    public AttendanceDTO getAttendanceById(Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new AttendanceNotFoundException("Attendance not found with id: " + id));

        return attendanceMapper.toDTO(attendance);
    }

    @Override
    public void deleteAttendance(Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new AttendanceNotFoundException("Attendance not found with id: " + id));

        attendanceRepository.delete(attendance);
    }

    @Override
    public List<AttendanceDTO> getAttendancesByEmployee(Long employeeId) {
        List<Attendance> attendances = attendanceRepository.findByEmployeeId(employeeId);
        return attendanceMapper.toDTOs(attendances);
    }

    @Override
    public List<AttendanceDTO> getAllAttendances() {
        List<Attendance> attendances = attendanceRepository.findAll();
        return attendanceMapper.toDTOs(attendances);
    }

    @Override
    public double calculateHoursWorked(Long employeeId) {
        List<Attendance> attendances = attendanceRepository.findByEmployeeId(employeeId);
        return attendances.stream().mapToDouble(Attendance::getHoursWorked).sum();
    }
}
