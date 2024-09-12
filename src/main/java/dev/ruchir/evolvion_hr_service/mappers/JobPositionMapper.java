package dev.ruchir.evolvion_hr_service.mappers;

import dev.ruchir.evolvion_hr_service.dto.JobPositionDTO;
import dev.ruchir.evolvion_hr_service.model.core.JobPosition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobPositionMapper {

    JobPositionMapper INSTANCE = Mappers.getMapper(JobPositionMapper.class);
    JobPositionDTO toDTO(JobPosition jobPosition);
    JobPosition toEntity(JobPositionDTO jobPositionDTO);
    List<JobPositionDTO> toDTOs(List<JobPosition> jobPositions);
    List<JobPosition> toEntities(List<JobPositionDTO> jobPositionDTOs);
}
