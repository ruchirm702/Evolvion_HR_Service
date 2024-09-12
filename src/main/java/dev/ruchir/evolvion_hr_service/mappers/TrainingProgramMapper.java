package dev.ruchir.evolvion_hr_service.mappers;

import dev.ruchir.evolvion_hr_service.dto.TrainingProgramDTO;
import dev.ruchir.evolvion_hr_service.model.core.TrainingProgram;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrainingProgramMapper {

    TrainingProgramMapper INSTANCE = Mappers.getMapper(TrainingProgramMapper.class);
    TrainingProgramDTO toDTO(TrainingProgram trainingProgram);
    TrainingProgram toEntity(TrainingProgramDTO trainingProgramDTO);
    List<TrainingProgramDTO> toDTOs(List<TrainingProgram> trainingPrograms);
    List<TrainingProgram> toEntities(List<TrainingProgramDTO> trainingProgramDTOs);
}
