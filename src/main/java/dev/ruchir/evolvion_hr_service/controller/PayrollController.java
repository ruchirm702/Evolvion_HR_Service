package dev.ruchir.evolvion_hr_service.controller;

import dev.ruchir.evolvion_hr_service.dto.PayrollDTO;
import dev.ruchir.evolvion_hr_service.service.interfaces.PayrollService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payrolls")
@RequiredArgsConstructor
public class PayrollController {

    private static final Logger logger = LoggerFactory.getLogger(PayrollController.class);

    private final PayrollService payrollService;

    // Endpoint to create a new payroll
    @PostMapping
    public ResponseEntity<PayrollDTO> createPayroll(@RequestBody PayrollDTO payrollDTO) {
        logger.info("Received request to create payroll for employee ID: {}", payrollDTO.getEmployeeId());
        PayrollDTO createdPayroll = payrollService.createPayroll(payrollDTO);
        return new ResponseEntity<>(createdPayroll, HttpStatus.CREATED);
    }

    // Endpoint to update an existing payroll by ID
    @PutMapping("/{payrollId}")
    public ResponseEntity<PayrollDTO> updatePayroll(
            @PathVariable Long payrollId,
            @RequestBody PayrollDTO payrollDTO) {
        logger.info("Received request to update payroll with ID: {}", payrollId);
        PayrollDTO updatedPayroll = payrollService.updatePayroll(payrollId, payrollDTO);
        return ResponseEntity.ok(updatedPayroll);
    }

    // Endpoint to get a payroll by ID
    @GetMapping("/{payrollId}")
    public ResponseEntity<PayrollDTO> getPayrollById(@PathVariable Long payrollId) {
        logger.info("Received request to fetch payroll with ID: {}", payrollId);
        PayrollDTO payrollDTO = payrollService.getPayrollById(payrollId);
        return ResponseEntity.ok(payrollDTO);
    }

    // Endpoint to delete a payroll by ID
    @DeleteMapping("/{payrollId}")
    public ResponseEntity<Void> deletePayroll(@PathVariable Long payrollId) {
        logger.info("Received request to delete payroll with ID: {}", payrollId);
        payrollService.deletePayroll(payrollId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoint to get all payrolls
    @GetMapping
    public ResponseEntity<List<PayrollDTO>> getAllPayrolls() {
        logger.info("Received request to fetch all payrolls");
        List<PayrollDTO> payrolls = payrollService.getAllPayrolls();
        return ResponseEntity.ok(payrolls);
    }

    // Endpoint to get payrolls by employee ID
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<PayrollDTO>> getPayrollsByEmployee(@PathVariable Long employeeId) {
        logger.info("Received request to fetch payrolls for employee ID: {}", employeeId);
        List<PayrollDTO> payrolls = payrollService.getPayrollsByEmployee(employeeId);
        return ResponseEntity.ok(payrolls);
    }

    // Endpoint to calculate payroll for an employee
    @GetMapping("/calculate/{employeeId}")
    public ResponseEntity<Double> calculatePayroll(@PathVariable Long employeeId) {
        logger.info("Received request to calculate payroll for employee ID: {}", employeeId);
        double totalPayroll = payrollService.calculatePayroll(employeeId);
        return ResponseEntity.ok(totalPayroll);
    }
}
