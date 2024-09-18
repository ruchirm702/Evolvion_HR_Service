package dev.ruchir.evolvion_hr_service.service.impl;

import dev.ruchir.evolvion_hr_service.controller_advise.JobPositionNotFoundException;
import dev.ruchir.evolvion_hr_service.dto.JobPositionDTO;
import dev.ruchir.evolvion_hr_service.mappers.JobPositionMapper;
import dev.ruchir.evolvion_hr_service.model.core.JobPosition;
import dev.ruchir.evolvion_hr_service.repository.JobPositionRepository;
import dev.ruchir.evolvion_hr_service.service.interfaces.JobPositionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPositionServiceImpl implements JobPositionService {

    private final JobPositionRepository jobPositionRepository;
    private final JobPositionMapper jobPositionMapper;
    private static final Logger logger = LoggerFactory.getLogger(JobPositionServiceImpl.class);

    @Override
    public JobPositionDTO createJobPosition(JobPositionDTO jobPositionDTO) {
        logger.info("Creating job position with title: {}", jobPositionDTO.getTitle());
        JobPosition jobPosition = jobPositionMapper.toEntity(jobPositionDTO);
        jobPosition = jobPositionRepository.save(jobPosition);
        return jobPositionMapper.toDTO(jobPosition);
    }

    @Override
    public JobPositionDTO updateJobPosition(Long id, JobPositionDTO jobPositionDTO) {
        logger.info("Updating job position with ID: {}", id);
        JobPosition existingJobPosition = jobPositionRepository.findById(id)
                .orElseThrow(() -> new JobPositionNotFoundException("Job position not found with id: " + id));

        existingJobPosition.setTitle(jobPositionDTO.getTitle());
        existingJobPosition.setDescription(jobPositionDTO.getDescription());
        existingJobPosition.setSalaryRangeMin(jobPositionDTO.getSalaryRangeMin());
        existingJobPosition.setSalaryRangeMax(jobPositionDTO.getSalaryRangeMax());
        existingJobPosition.setDepartment(jobPositionMapper.toEntity(jobPositionDTO).getDepartment());

        jobPositionRepository.save(existingJobPosition);
        return jobPositionMapper.toDTO(existingJobPosition);
    }

    @Override
    public JobPositionDTO getJobPositionById(Long id) {
        logger.info("Fetching job position by ID: {}", id);
        JobPosition jobPosition = jobPositionRepository.findById(id)
                .orElseThrow(() -> new JobPositionNotFoundException("Job position not found with id: " + id));
        return jobPositionMapper.toDTO(jobPosition);
    }

    @Override
    public void deleteJobPosition(Long id) {
        logger.info("Deleting job position by ID: {}", id);
        JobPosition jobPosition = jobPositionRepository.findById(id)
                .orElseThrow(() -> new JobPositionNotFoundException("Job position not found with id: " + id));
        jobPositionRepository.delete(jobPosition);
    }

    @Override
    public List<JobPositionDTO> getAllJobPositions() {
        logger.info("Fetching all job positions");
        List<JobPosition> jobPositions = jobPositionRepository.findAll();
        return jobPositionMapper.toDTOs(jobPositions);
    }

    @Override
    public List<JobPositionDTO> getJobPositionsByDepartmentId(Long departmentId) {
        logger.info("Fetching job positions for department ID: {}", departmentId);
        List<JobPosition> jobPositions = jobPositionRepository.findByDepartmentId(departmentId);
        return jobPositionMapper.toDTOs(jobPositions);
    }

    @Override
    public List<JobPositionDTO> getJobPositionsBySalaryRange(Double minSalary, Double maxSalary) {
        logger.info("Fetching job positions with salary range: {} - {}", minSalary, maxSalary);
        List<JobPosition> jobPositions = jobPositionRepository.findBySalaryRangeMinGreaterThanEqualAndSalaryRangeMaxLessThanEqual(minSalary, maxSalary);
        return jobPositionMapper.toDTOs(jobPositions);
    }

    @Override
    public List<JobPositionDTO> searchJobPositionsByTitle(String partialTitle) {
        logger.info("Searching job positions by title containing: {}", partialTitle);
        List<JobPosition> jobPositions = jobPositionRepository.findByTitleContainingIgnoreCase(partialTitle);
        return jobPositionMapper.toDTOs(jobPositions);
    }
}
