package dev.ruchir.evolvion_hr_service.mappers;

import dev.ruchir.evolvion_hr_service.dto.AttendanceDTO;
import dev.ruchir.evolvion_hr_service.model.core.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {

    AttendanceMapper INSTANCE = Mappers.getMapper(AttendanceMapper.class);
    AttendanceDTO toDTO(Attendance attendance);
    Attendance toEntity(AttendanceDTO attendanceDTO);
    List<AttendanceDTO> toDTOs(List<Attendance> attendances);
    List<Attendance> toEntities(List<AttendanceDTO> attendanceDTOs);
}
