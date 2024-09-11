package dev.ruchir.evolvion_hr_service.dto;

import dev.ruchir.evolvion_hr_service.model.enums.TrainingType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TrainingProgramDTO {
    private Long id;
    private String programName;
    private String description;
    private TrainingType type;
    private Date startDate;
    private Date endDate;
    private List<Long> participantIds; // Only storing participant IDs in DTO
}
