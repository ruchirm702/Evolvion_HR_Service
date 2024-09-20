package dev.ruchir.evolvion_hr_service.controller;

import dev.ruchir.evolvion_hr_service.dto.TrainingProgramDTO;
import dev.ruchir.evolvion_hr_service.model.enums.TrainingType;
import dev.ruchir.evolvion_hr_service.service.interfaces.TrainingProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/training-programs")
@RequiredArgsConstructor
public class TrainingProgramController {

    private final TrainingProgramService trainingProgramService;

    // Create a new training program
    @PostMapping
    public ResponseEntity<TrainingProgramDTO> createTrainingProgram(@RequestBody TrainingProgramDTO trainingProgramDTO) {
        TrainingProgramDTO createdProgram = trainingProgramService.createTrainingProgram(trainingProgramDTO);
        return new ResponseEntity<>(createdProgram, HttpStatus.CREATED);
    }

    // Update an existing training program
    @PutMapping("/{id}")
    public ResponseEntity<TrainingProgramDTO> updateTrainingProgram(@PathVariable Long id, @RequestBody TrainingProgramDTO trainingProgramDTO) {
        TrainingProgramDTO updatedProgram = trainingProgramService.updateTrainingProgram(id, trainingProgramDTO);
        return new ResponseEntity<>(updatedProgram, HttpStatus.OK);
    }

    // Delete a training program
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainingProgram(@PathVariable Long id) {
        trainingProgramService.deleteTrainingProgram(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Retrieve a training program by ID
    @GetMapping("/{id}")
    public ResponseEntity<TrainingProgramDTO> getTrainingProgramById(@PathVariable Long id) {
        TrainingProgramDTO trainingProgram = trainingProgramService.getTrainingProgramById(id);
        return new ResponseEntity<>(trainingProgram, HttpStatus.OK);
    }

    // Retrieve all training programs
    @GetMapping
    public ResponseEntity<List<TrainingProgramDTO>> getAllTrainingPrograms() {
        List<TrainingProgramDTO> trainingPrograms = trainingProgramService.getAllTrainingPrograms();
        return new ResponseEntity<>(trainingPrograms, HttpStatus.OK);
    }

    // Retrieve training programs by type
    @GetMapping("/type/{type}")
    public ResponseEntity<List<TrainingProgramDTO>> getTrainingProgramsByType(@PathVariable String type) {
        List<TrainingProgramDTO> trainingPrograms = trainingProgramService.getTrainingProgramsByType(TrainingType.valueOf(type.toUpperCase()));
        return new ResponseEntity<>(trainingPrograms, HttpStatus.OK);
    }

    // Retrieve training programs starting after a specific date
    @GetMapping("/starting-after")
    public ResponseEntity<List<TrainingProgramDTO>> getTrainingProgramsStartingAfter(@RequestParam Date startDate) {
        List<TrainingProgramDTO> trainingPrograms = trainingProgramService.getTrainingProgramsStartingAfter(startDate);
        return new ResponseEntity<>(trainingPrograms, HttpStatus.OK);
    }

    // Retrieve training programs ending before a specific date
    @GetMapping("/ending-before")
    public ResponseEntity<List<TrainingProgramDTO>> getTrainingProgramsEndingBefore(@RequestParam Date endDate) {
        List<TrainingProgramDTO> trainingPrograms = trainingProgramService.getTrainingProgramsEndingBefore(endDate);
        return new ResponseEntity<>(trainingPrograms, HttpStatus.OK);
    }

    // Retrieve training programs within a specific date range
    @GetMapping("/within-date-range")
    public ResponseEntity<List<TrainingProgramDTO>> getTrainingProgramsWithinDateRange(@RequestParam Date startDate, @RequestParam Date endDate) {
        List<TrainingProgramDTO> trainingPrograms = trainingProgramService.getTrainingProgramsWithinDateRange(startDate, endDate);
        return new ResponseEntity<>(trainingPrograms, HttpStatus.OK);
    }

    // Check if a training program with a specific name exists
    @GetMapping("/exists")
    public ResponseEntity<Boolean> isTrainingProgramNameExists(@RequestParam String programName) {
        boolean exists = trainingProgramService.isTrainingProgramNameExists(programName);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    // Retrieve all training programs for a specific employee
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<TrainingProgramDTO>> getTrainingProgramsByEmployee(@PathVariable Long employeeId) {
        List<TrainingProgramDTO> trainingPrograms = trainingProgramService.getTrainingProgramsByEmployee(employeeId);
        return new ResponseEntity<>(trainingPrograms, HttpStatus.OK);
    }
}
