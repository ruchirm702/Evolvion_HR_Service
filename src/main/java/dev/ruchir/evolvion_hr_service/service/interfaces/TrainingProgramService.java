package dev.ruchir.evolvion_hr_service.service.interfaces;

import dev.ruchir.evolvion_hr_service.dto.TrainingProgramDTO;
import dev.ruchir.evolvion_hr_service.model.enums.TrainingType;

import java.util.Date;
import java.util.List;

public interface TrainingProgramService {

    // Create a new training program
    TrainingProgramDTO createTrainingProgram(TrainingProgramDTO trainingProgramDTO);

    // Update an existing training program
    TrainingProgramDTO updateTrainingProgram(Long id, TrainingProgramDTO trainingProgramDTO);

    // Delete a training program
    void deleteTrainingProgram(Long id);

    // Retrieve a training program by ID
    TrainingProgramDTO getTrainingProgramById(Long id);

    // Retrieve all training programs
    List<TrainingProgramDTO> getAllTrainingPrograms();

    // Retrieve training programs by type
    List<TrainingProgramDTO> getTrainingProgramsByType(TrainingType type);

    // Retrieve training programs starting after a specific date
    List<TrainingProgramDTO> getTrainingProgramsStartingAfter(Date startDate);

    // Retrieve training programs ending before a specific date
    List<TrainingProgramDTO> getTrainingProgramsEndingBefore(Date endDate);

    // Retrieve training programs within a date range
    List<TrainingProgramDTO> getTrainingProgramsWithinDateRange(Date startDate, Date endDate);

    // Check if a training program with a specific name exists
    boolean isTrainingProgramNameExists(String programName);

    // Retrieve all training programs for a specific employee
    List<TrainingProgramDTO> getTrainingProgramsByEmployee(Long employeeId);
}
