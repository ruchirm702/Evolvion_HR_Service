package dev.ruchir.evolvion_hr_service.repository;

import dev.ruchir.evolvion_hr_service.model.core.TrainingProgram;
import dev.ruchir.evolvion_hr_service.model.enums.TrainingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TrainingProgramRepository extends JpaRepository<TrainingProgram, Long> {

    // Find all training programs by type
    List<TrainingProgram> findByType(TrainingType type);

    // Find all training programs starting after a specific date
    List<TrainingProgram> findByStartDateAfter(Date startDate);

    // Find all training programs ending before a specific date
    List<TrainingProgram> findByEndDateBefore(Date endDate);

    // Find all training programs within a date range
    List<TrainingProgram> findByStartDateBetween(Date startDate, Date endDate);

    // Check if a training program with a given name exists
    boolean existsByProgramName(String programName);

    // Find all training programs for a specific employee
    List<TrainingProgram> findByParticipants_Id(Long employeeId);
}
