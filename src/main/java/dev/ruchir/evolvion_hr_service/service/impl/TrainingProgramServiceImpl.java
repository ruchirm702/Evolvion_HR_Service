package dev.ruchir.evolvion_hr_service.service.impl;

import dev.ruchir.evolvion_hr_service.controller_advise.TrainingProgramNotFoundException;
import dev.ruchir.evolvion_hr_service.dto.TrainingProgramDTO;
import dev.ruchir.evolvion_hr_service.mappers.TrainingProgramMapper;
import dev.ruchir.evolvion_hr_service.model.core.TrainingProgram;
import dev.ruchir.evolvion_hr_service.model.enums.TrainingType;
import dev.ruchir.evolvion_hr_service.repository.TrainingProgramRepository;
import dev.ruchir.evolvion_hr_service.service.interfaces.TrainingProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainingProgramServiceImpl implements TrainingProgramService {

    private final TrainingProgramRepository trainingProgramRepository;
    private final TrainingProgramMapper trainingProgramMapper;

    @Override
    public TrainingProgramDTO createTrainingProgram(TrainingProgramDTO trainingProgramDTO) {
        TrainingProgram trainingProgram = trainingProgramMapper.toEntity(trainingProgramDTO);
        trainingProgram = trainingProgramRepository.save(trainingProgram);
        return trainingProgramMapper.toDTO(trainingProgram);
    }

    @Override
    public TrainingProgramDTO updateTrainingProgram(Long id, TrainingProgramDTO trainingProgramDTO) {
        TrainingProgram existingProgram = trainingProgramRepository.findById(id)
                .orElseThrow(() -> new TrainingProgramNotFoundException("Training program not found with ID: " + id));

        // Update fields
        existingProgram.setProgramName(trainingProgramDTO.getProgramName());
        existingProgram.setDescription(trainingProgramDTO.getDescription());
        existingProgram.setType(trainingProgramDTO.getType());
        existingProgram.setStartDate(trainingProgramDTO.getStartDate());
        existingProgram.setEndDate(trainingProgramDTO.getEndDate());

        existingProgram = trainingProgramRepository.save(existingProgram);
        return trainingProgramMapper.toDTO(existingProgram);
    }

    @Override
    public void deleteTrainingProgram(Long id) {
        if (!trainingProgramRepository.existsById(id)) {
            throw new TrainingProgramNotFoundException("Training program not found with ID: " + id);
        }
        trainingProgramRepository.deleteById(id);
    }

    @Override
    public TrainingProgramDTO getTrainingProgramById(Long id) {
        TrainingProgram trainingProgram = trainingProgramRepository.findById(id)
                .orElseThrow(() -> new TrainingProgramNotFoundException("Training program not found with ID: " + id));
        return trainingProgramMapper.toDTO(trainingProgram);
    }

    @Override
    public List<TrainingProgramDTO> getAllTrainingPrograms() {
        return trainingProgramRepository.findAll().stream()
                .map(trainingProgramMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainingProgramDTO> getTrainingProgramsByType(TrainingType type) {
        return trainingProgramRepository.findByType(type).stream()
                .map(trainingProgramMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainingProgramDTO> getTrainingProgramsStartingAfter(Date startDate) {
        return trainingProgramRepository.findByStartDateAfter(startDate).stream()
                .map(trainingProgramMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainingProgramDTO> getTrainingProgramsEndingBefore(Date endDate) {
        return trainingProgramRepository.findByEndDateBefore(endDate).stream()
                .map(trainingProgramMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainingProgramDTO> getTrainingProgramsWithinDateRange(Date startDate, Date endDate) {
        return trainingProgramRepository.findByStartDateBetween(startDate, endDate).stream()
                .map(trainingProgramMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isTrainingProgramNameExists(String programName) {
        return trainingProgramRepository.existsByProgramName(programName);
    }

    @Override
    public List<TrainingProgramDTO> getTrainingProgramsByEmployee(Long employeeId) {
        return trainingProgramRepository.findByParticipants_Id(employeeId).stream()
                .map(trainingProgramMapper::toDTO)
                .collect(Collectors.toList());
    }
}
