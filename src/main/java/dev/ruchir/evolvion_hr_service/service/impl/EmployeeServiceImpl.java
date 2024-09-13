package dev.ruchir.evolvion_hr_service.service.impl;

import dev.ruchir.evolvion_hr_service.controller_advise.EmployeeAlreadyExistsException;
import dev.ruchir.evolvion_hr_service.controller_advise.EmployeeNotFoundException;
import dev.ruchir.evolvion_hr_service.dto.EmployeeDTO;
import dev.ruchir.evolvion_hr_service.mappers.EmployeeMapper;
import dev.ruchir.evolvion_hr_service.model.core.Employee;
import dev.ruchir.evolvion_hr_service.model.enums.EmployeeRole;
import dev.ruchir.evolvion_hr_service.repository.EmployeeRepository;
import dev.ruchir.evolvion_hr_service.service.interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        logger.debug("Creating employee with email: {}", employeeDTO.getEmail());
        Optional<Employee> existingEmployee = employeeRepository.findByEmail(employeeDTO.getEmail());
        if (existingEmployee.isPresent()) {
            logger.warn("Employee with email {} already exists.", employeeDTO.getEmail());
            throw new EmployeeAlreadyExistsException("Employee with email " + employeeDTO.getEmail() + " already exists.");
        }

        Employee employee = employeeMapper.toEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        logger.info("Employee created with ID: {}", savedEmployee.getId());

        return employeeMapper.toDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO employeeDTO) {
        logger.debug("Updating employee with ID: {}", employeeId);
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> {
                    logger.error("Employee with ID {} not found", employeeId);
                    return new EmployeeNotFoundException("Employee with ID " + employeeId + " not found");
                });

        existingEmployee.setFirstName(employeeDTO.getFirstName());
        existingEmployee.setLastName(employeeDTO.getLastName());
        existingEmployee.setEmail(employeeDTO.getEmail());
        existingEmployee.setPhoneNumber(employeeDTO.getPhoneNumber());
        existingEmployee.setRole(EmployeeRole.valueOf(employeeDTO.getRole().toString()));
        existingEmployee.setStatus(employeeDTO.getStatus());

        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        logger.info("Employee updated with ID: {}", updatedEmployee.getId());

        return employeeMapper.toDTO(updatedEmployee);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        logger.debug("Fetching employee with ID: {}", employeeId);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> {
                    logger.error("Employee with ID {} not found", employeeId);
                    return new EmployeeNotFoundException("Employee with ID " + employeeId + " not found");
                });
        return employeeMapper.toDTO(employee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        logger.debug("Deleting employee with ID: {}", employeeId);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> {
                    logger.error("Employee with ID {} not found", employeeId);
                    return new EmployeeNotFoundException("Employee with ID " + employeeId + " not found");
                });
        employeeRepository.delete(employee);
        logger.info("Employee deleted with ID: {}", employeeId);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        logger.debug("Fetching all employees");
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getEmployeesByDepartment(Long departmentId) {
        logger.debug("Fetching employees by department ID: {}", departmentId);
        List<Employee> employees = employeeRepository.findAllByDepartmentId(departmentId);
        return employees.stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getEmployeesByRole(String role) {
        logger.debug("Fetching employees by role: {}", role);
        List<Employee> employees = employeeRepository.findAllByRole(EmployeeRole.valueOf(role.toUpperCase()));
        return employees.stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }
}
