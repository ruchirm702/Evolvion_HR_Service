package dev.ruchir.evolvion_hr_service.service.impl;

import dev.ruchir.evolvion_hr_service.controller_advise.JobPositionNotFoundException;
import dev.ruchir.evolvion_hr_service.dto.JobPositionDTO;
import dev.ruchir.evolvion_hr_service.mappers.JobPositionMapper;
import dev.ruchir.evolvion_hr_service.model.core.JobPosition;
import dev.ruchir.evolvion_hr_service.repository.JobPositionRepository;
import dev.ruchir.evolvion_hr_service.service.interfaces.JobPositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPositionServiceImpl implements JobPositionService {

    private final JobPositionRepository jobPositionRepository;
    private final JobPositionMapper jobPositionMapper;

    @Override
    public JobPositionDTO createJobPosition(JobPositionDTO jobPositionDTO) {
        JobPosition jobPosition = jobPositionMapper.toEntity(jobPositionDTO);
        jobPosition = jobPositionRepository.save(jobPosition);
        return jobPositionMapper.toDTO(jobPosition);
    }

    @Override
    public JobPositionDTO updateJobPosition(Long id, JobPositionDTO jobPositionDTO) {
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
        JobPosition jobPosition = jobPositionRepository.findById(id)
                .orElseThrow(() -> new JobPositionNotFoundException("Job position not found with id: " + id));
        return jobPositionMapper.toDTO(jobPosition);
    }

    @Override
    public void deleteJobPosition(Long id) {
        JobPosition jobPosition = jobPositionRepository.findById(id)
                .orElseThrow(() -> new JobPositionNotFoundException("Job position not found with id: " + id));
        jobPositionRepository.delete(jobPosition);
    }

    @Override
    public List<JobPositionDTO> getAllJobPositions() {
        List<JobPosition> jobPositions = jobPositionRepository.findAll();
        return jobPositionMapper.toDTOs(jobPositions);
    }

    @Override
    public List<JobPositionDTO> getJobPositionsByDepartmentId(Long departmentId) {
        List<JobPosition> jobPositions = jobPositionRepository.findByDepartmentId(departmentId);
        return jobPositionMapper.toDTOs(jobPositions);
    }

    @Override
    public List<JobPositionDTO> getJobPositionsBySalaryRange(Double minSalary, Double maxSalary) {
        List<JobPosition> jobPositions = jobPositionRepository.findBySalaryRangeMinGreaterThanEqualAndSalaryRangeMaxLessThanEqual(minSalary, maxSalary);
        return jobPositionMapper.toDTOs(jobPositions);
    }

    @Override
    public List<JobPositionDTO> searchJobPositionsByTitle(String partialTitle) {
        List<JobPosition> jobPositions = jobPositionRepository.findByTitleContainingIgnoreCase(partialTitle);
        return jobPositionMapper.toDTOs(jobPositions);
    }
}
