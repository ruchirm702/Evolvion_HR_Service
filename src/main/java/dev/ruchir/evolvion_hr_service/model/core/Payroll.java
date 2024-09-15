package dev.ruchir.evolvion_hr_service.model.core;

import dev.ruchir.evolvion_hr_service.model.enums.PayrollStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "payrolls")
@Getter
@Setter
public class Payroll extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PayrollStatus status;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;  // Updated to LocalDate
}
