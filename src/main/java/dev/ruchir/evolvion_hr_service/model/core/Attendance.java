package dev.ruchir.evolvion_hr_service.model.core;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "attendances")
@Getter
@Setter
public class Attendance extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "check_in_time", nullable = false)
    private Date checkInTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "check_out_time")
    private Date checkOutTime;

    @Column(name = "hours_worked", nullable = false)
    private Double hoursWorked;
}
