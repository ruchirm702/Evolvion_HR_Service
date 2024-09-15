package dev.ruchir.evolvion_hr_service.dto;

import dev.ruchir.evolvion_hr_service.model.enums.PayrollStatus;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class PayrollDTO {
    private Long id;
    private Long employeeId;
    private Double amount;
    private PayrollStatus status;
    private LocalDate paymentDate;
}
