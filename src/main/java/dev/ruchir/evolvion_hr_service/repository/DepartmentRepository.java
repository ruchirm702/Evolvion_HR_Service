package dev.ruchir.evolvion_hr_service.repository;

import dev.ruchir.evolvion_hr_service.model.core.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Find department by name (assuming names are unique)
    Optional<Department> findByName(String name);

    // Find all departments with a specific employee ID
    List<Department> findAllByEmployeesId(Long employeeId);
}
