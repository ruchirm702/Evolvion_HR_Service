package dev.ruchir.evolvion_hr_service.dto;

import dev.ruchir.evolvion_hr_service.model.enums.PerformanceReviewStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PerformanceReviewDTO {
    private Long id;
    private Long employeeId;
    private Long reviewerId;
    private Integer score;
    private String comments;
    private Date reviewDate;
    private PerformanceReviewStatus status;
}
