package dev.ruchir.evolvion_hr_service.repository;

import dev.ruchir.evolvion_hr_service.model.core.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // Find attendance records by employee ID
    List<Attendance> findByEmployeeId(Long employeeId);

    // Find attendance by employee ID and check-in date
    Optional<Attendance> findByEmployeeIdAndCheckInTime(Long employeeId, java.util.Date checkInTime);

    // Custom query to find all attendances between specific dates
    List<Attendance> findByCheckInTimeBetween(java.util.Date startDate, java.util.Date endDate);
}
