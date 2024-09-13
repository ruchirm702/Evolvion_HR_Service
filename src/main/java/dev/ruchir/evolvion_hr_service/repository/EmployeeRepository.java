package dev.ruchir.evolvion_hr_service.repository;


import dev.ruchir.evolvion_hr_service.model.core.Employee;
import dev.ruchir.evolvion_hr_service.model.enums.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Find employee by email (assuming email is unique)
    Optional<Employee> findByEmail(String email);

    // Find all employees by role
    List<Employee> findAllByRole(EmployeeRole role);

    // Find all employees by department ID
    List<Employee> findAllByDepartmentId(Long departmentId);
}
