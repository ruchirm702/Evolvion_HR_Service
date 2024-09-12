package dev.ruchir.evolvion_hr_service.mappers;

import dev.ruchir.evolvion_hr_service.dto.PerformanceReviewDTO;
import dev.ruchir.evolvion_hr_service.model.core.PerformanceReview;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PerformanceReviewMapper {

    PerformanceReviewMapper INSTANCE = Mappers.getMapper(PerformanceReviewMapper.class);
    PerformanceReviewDTO toDTO(PerformanceReview performanceReview);
    PerformanceReview toEntity(PerformanceReviewDTO performanceReviewDTO);
    List<PerformanceReviewDTO> toDTOs(List<PerformanceReview> performanceReviews);
    List<PerformanceReview> toEntities(List<PerformanceReviewDTO> performanceReviewDTOs);
}
