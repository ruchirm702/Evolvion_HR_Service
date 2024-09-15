package dev.ruchir.evolvion_hr_service.service.interfaces;

import dev.ruchir.evolvion_hr_service.dto.PayrollDTO;
import java.util.List;

public interface PayrollService {

    // Process new payroll
    PayrollDTO createPayroll(PayrollDTO payrollDTO);

    // Update existing payroll
    PayrollDTO updatePayroll(Long payrollId, PayrollDTO payrollDTO);

    // Get payroll by ID
    PayrollDTO getPayrollById(Long payrollId);

    // Delete payroll by ID
    void deletePayroll(Long payrollId);

    // Get all payroll records
    List<PayrollDTO> getAllPayrolls();

    // Get payrolls by employee ID
    List<PayrollDTO> getPayrollsByEmployee(Long employeeId);

    // Calculate payroll based on salary and hours worked (custom logic can be added)
    double calculatePayroll(Long employeeId);
}
