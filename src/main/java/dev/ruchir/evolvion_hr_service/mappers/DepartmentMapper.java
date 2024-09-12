package dev.ruchir.evolvion_hr_service.mappers;

import dev.ruchir.evolvion_hr_service.dto.DepartmentDTO;
import dev.ruchir.evolvion_hr_service.model.core.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);
    DepartmentDTO toDTO(Department department);
    Department toEntity(DepartmentDTO departmentDTO);
    List<DepartmentDTO> toDTOs(List<Department> departments);
    List<Department> toEntities(List<DepartmentDTO> departmentDTOs);
}
