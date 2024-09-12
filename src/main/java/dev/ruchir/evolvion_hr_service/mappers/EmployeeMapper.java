package dev.ruchir.evolvion_hr_service.mappers;

import dev.ruchir.evolvion_hr_service.dto.EmployeeDTO;
import dev.ruchir.evolvion_hr_service.model.core.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
    EmployeeDTO toDTO(Employee employee);
    Employee toEntity(EmployeeDTO employeeDTO);
    List<EmployeeDTO> toDTOs(List<Employee> employees);
    List<Employee> toEntities(List<EmployeeDTO> employeeDTOs);
}
