package dev.ruchir.evolvion_hr_service.model.core;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "performance_reviews")
@Getter
@Setter
public class PerformanceReview extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id", nullable = false)
    private Employee reviewer; // The person doing the review

    @Column(name = "score", nullable = false)
    private Integer score; // 1-5 or other scale

    @Column(name = "comments")
    private String comments;

    @Temporal(TemporalType.DATE)
    @Column(name = "review_date", nullable = false)
    private Date reviewDate;

    @Column(name = "status", nullable = false)
    private String status; // PENDING, COMPLETED, APPROVED, REJECTED
}
