package dev.ruchir.evolvion_hr_service.mappers;

import dev.ruchir.evolvion_hr_service.dto.LeaveRequestDTO;
import dev.ruchir.evolvion_hr_service.model.core.LeaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LeaveRequestMapper {

    LeaveRequestMapper INSTANCE = Mappers.getMapper(LeaveRequestMapper.class);
    LeaveRequestDTO toDTO(LeaveRequest leaveRequest);
    LeaveRequest toEntity(LeaveRequestDTO leaveRequestDTO);
    List<LeaveRequestDTO> toDTOs(List<LeaveRequest> leaveRequests);
    List<LeaveRequest> toEntities(List<LeaveRequestDTO> leaveRequestDTOs);
}
