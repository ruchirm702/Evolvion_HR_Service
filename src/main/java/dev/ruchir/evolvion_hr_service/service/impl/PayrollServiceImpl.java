package dev.ruchir.evolvion_hr_service.service.impl;

import dev.ruchir.evolvion_hr_service.controller_advise.PayrollAlreadyExistsException;
import dev.ruchir.evolvion_hr_service.controller_advise.PayrollNotFoundException;
import dev.ruchir.evolvion_hr_service.dto.PayrollDTO;
import dev.ruchir.evolvion_hr_service.mappers.PayrollMapper;
import dev.ruchir.evolvion_hr_service.model.core.Payroll;
import dev.ruchir.evolvion_hr_service.repository.PayrollRepository;
import dev.ruchir.evolvion_hr_service.service.interfaces.PayrollService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PayrollServiceImpl implements PayrollService {

    private static final Logger logger = LoggerFactory.getLogger(PayrollServiceImpl.class);

    private final PayrollRepository payrollRepository;
    private final PayrollMapper payrollMapper;

    @Override
    public PayrollDTO createPayroll(PayrollDTO payrollDTO) {
        logger.info("Attempting to create payroll for employee ID: {}", payrollDTO.getEmployeeId());

        Optional<Payroll> existingPayroll = payrollRepository.findByEmployeeIdAndPaymentDate(payrollDTO.getEmployeeId(), payrollDTO.getPaymentDate());
        if (existingPayroll.isPresent()) {
            logger.error("Payroll already exists for employee ID {} on date {}", payrollDTO.getEmployeeId(), payrollDTO.getPaymentDate());
            throw new PayrollAlreadyExistsException("Payroll already exists for employee ID " + payrollDTO.getEmployeeId() + " on " + payrollDTO.getPaymentDate());
        }

        Payroll payroll = payrollMapper.toEntity(payrollDTO);
        Payroll savedPayroll = payrollRepository.save(payroll);
        logger.info("Payroll created successfully with ID: {}", savedPayroll.getId());

        return payrollMapper.toDTO(savedPayroll);
    }

    @Override
    public PayrollDTO updatePayroll(Long payrollId, PayrollDTO payrollDTO) {
        logger.info("Attempting to update payroll with ID: {}", payrollId);

        Payroll existingPayroll = payrollRepository.findById(payrollId)
                .orElseThrow(() -> new PayrollNotFoundException("Payroll with ID " + payrollId + " not found"));

        existingPayroll.setAmount(payrollDTO.getAmount());
        existingPayroll.setStatus(payrollDTO.getStatus());
        existingPayroll.setPaymentDate(payrollDTO.getPaymentDate());

        Payroll updatedPayroll = payrollRepository.save(existingPayroll);
        logger.info("Payroll updated successfully with ID: {}", updatedPayroll.getId());

        return payrollMapper.toDTO(updatedPayroll);
    }

    @Override
    public PayrollDTO getPayrollById(Long payrollId) {
        logger.info("Fetching payroll with ID: {}", payrollId);

        Payroll payroll = payrollRepository.findById(payrollId)
                .orElseThrow(() -> new PayrollNotFoundException("Payroll with ID " + payrollId + " not found"));

        return payrollMapper.toDTO(payroll);
    }

    @Override
    public void deletePayroll(Long payrollId) {
        logger.info("Attempting to delete payroll with ID: {}", payrollId);

        Payroll payroll = payrollRepository.findById(payrollId)
                .orElseThrow(() -> new PayrollNotFoundException("Payroll with ID " + payrollId + " not found"));

        payrollRepository.delete(payroll);
        logger.info("Payroll deleted successfully with ID: {}", payrollId);
    }

    @Override
    public List<PayrollDTO> getAllPayrolls() {
        logger.info("Fetching all payroll records");

        return payrollRepository.findAll().stream()
                .map(payrollMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PayrollDTO> getPayrollsByEmployee(Long employeeId) {
        logger.info("Fetching payrolls for employee ID: {}", employeeId);

        return payrollRepository.findAllByEmployeeId(employeeId).stream()
                .map(payrollMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public double calculatePayroll(Long employeeId) {
        logger.info("Calculating payroll for employee ID: {}", employeeId);

        return payrollRepository.findAllByEmployeeId(employeeId).stream()
                .mapToDouble(Payroll::getAmount)
                .sum();
    }
}
