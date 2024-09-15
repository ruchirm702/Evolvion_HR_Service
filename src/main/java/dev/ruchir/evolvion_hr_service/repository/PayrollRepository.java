package dev.ruchir.evolvion_hr_service.repository;

import dev.ruchir.evolvion_hr_service.model.core.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {

    Optional<Payroll> findByEmployeeIdAndPaymentDate(Long employeeId, LocalDate paymentDate);
    List<Payroll> findAllByEmployeeId(Long employeeId);
    List<Payroll> findAllByStatus(String status);
}
