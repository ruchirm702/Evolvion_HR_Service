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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        // Check if employee with same email exists
        Optional<Employee> existingEmployee = employeeRepository.findByEmail(employeeDTO.getEmail());
        if (existingEmployee.isPresent()) {
            throw new EmployeeAlreadyExistsException("Employee with email " + employeeDTO.getEmail() + " already exists.");
        }

        // Map DTO to entity
        Employee employee = employeeMapper.toEntity(employeeDTO);

        // Save employee in the database
        Employee savedEmployee = employeeRepository.save(employee);

        // Return the saved employee as DTO
        return employeeMapper.toDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO employeeDTO) {
        // Retrieve employee by ID
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + employeeId + " not found"));

        // Update employee details
        existingEmployee.setFirstName(employeeDTO.getFirstName());
        existingEmployee.setLastName(employeeDTO.getLastName());
        existingEmployee.setEmail(employeeDTO.getEmail());
        existingEmployee.setPhoneNumber(employeeDTO.getPhoneNumber());
        existingEmployee.setRole(EmployeeRole.valueOf(employeeDTO.getRole().toString()));
        existingEmployee.setStatus(employeeDTO.getStatus());

        // Save updated employee
        Employee updatedEmployee = employeeRepository.save(existingEmployee);

        // Return the updated employee as DTO
        return employeeMapper.toDTO(updatedEmployee);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + employeeId + " not found"));
        return employeeMapper.toDTO(employee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + employeeId + " not found"));
        employeeRepository.delete(employee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getEmployeesByDepartment(Long departmentId) {
        List<Employee> employees = employeeRepository.findAllByDepartmentId(departmentId);
        return employees.stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getEmployeesByRole(String role) {
        List<Employee> employees = employeeRepository.findAllByRole(EmployeeRole.valueOf(role.toUpperCase()));
        return employees.stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }
}
