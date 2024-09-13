package dev.ruchir.evolvion_hr_service.service.impl;

import dev.ruchir.evolvion_hr_service.controller_advise.DepartmentAlreadyExistsException;
import dev.ruchir.evolvion_hr_service.controller_advise.DepartmentNotFoundException;
import dev.ruchir.evolvion_hr_service.dto.DepartmentDTO;
import dev.ruchir.evolvion_hr_service.mappers.DepartmentMapper;
import dev.ruchir.evolvion_hr_service.model.core.Department;
import dev.ruchir.evolvion_hr_service.repository.DepartmentRepository;
import dev.ruchir.evolvion_hr_service.service.interfaces.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        logger.info("Attempting to create department with name: {}", departmentDTO.getName());

        Optional<Department> existingDepartment = departmentRepository.findByName(departmentDTO.getName());
        if (existingDepartment.isPresent()) {
            logger.error("Department with name {} already exists.", departmentDTO.getName());
            throw new DepartmentAlreadyExistsException("Department with name " + departmentDTO.getName() + " already exists.");
        }

        Department department = departmentMapper.toEntity(departmentDTO);
        Department savedDepartment = departmentRepository.save(department);
        logger.info("Department created successfully with ID: {}", savedDepartment.getId());

        return departmentMapper.toDTO(savedDepartment);
    }

    @Override
    public DepartmentDTO updateDepartment(Long departmentId, DepartmentDTO departmentDTO) {
        logger.info("Attempting to update department with ID: {}", departmentId);

        Department existingDepartment = departmentRepository.findById(departmentId)
                .orElseThrow(() -> {
                    logger.error("Department with ID {} not found", departmentId);
                    return new DepartmentNotFoundException("Department with ID " + departmentId + " not found");
                });

        existingDepartment.setName(departmentDTO.getName());
        Department updatedDepartment = departmentRepository.save(existingDepartment);
        logger.info("Department updated successfully with ID: {}", updatedDepartment.getId());

        return departmentMapper.toDTO(updatedDepartment);
    }

    @Override
    public DepartmentDTO getDepartmentById(Long departmentId) {
        logger.info("Fetching department with ID: {}", departmentId);

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> {
                    logger.error("Department with ID {} not found", departmentId);
                    return new DepartmentNotFoundException("Department with ID " + departmentId + " not found");
                });

        return departmentMapper.toDTO(department);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        logger.info("Attempting to delete department with ID: {}", departmentId);

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> {
                    logger.error("Department with ID {} not found", departmentId);
                    return new DepartmentNotFoundException("Department with ID " + departmentId + " not found");
                });

        departmentRepository.delete(department);
        logger.info("Department deleted successfully with ID: {}", departmentId);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        logger.info("Fetching all departments");

        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(departmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO getDepartmentByName(String name) {
        logger.info("Fetching department with name: {}", name);

        Department department = departmentRepository.findByName(name)
                .orElseThrow(() -> {
                    logger.error("Department with name {} not found", name);
                    return new DepartmentNotFoundException("Department with name " + name + " not found");
                });

        return departmentMapper.toDTO(department);
    }

    @Override
    public List<DepartmentDTO> getDepartmentsByEmployee(Long employeeId) {
        logger.info("Fetching departments for employee ID: {}", employeeId);

        List<Department> departments = departmentRepository.findAllByEmployeesId(employeeId);
        return departments.stream()
                .map(departmentMapper::toDTO)
                .collect(Collectors.toList());
    }
}
