package dev.ruchir.evolvion_hr_service.mappers;

import dev.ruchir.evolvion_hr_service.dto.PayrollDTO;
import dev.ruchir.evolvion_hr_service.model.core.Payroll;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PayrollMapper {

    PayrollMapper INSTANCE = Mappers.getMapper(PayrollMapper.class);
    PayrollDTO toDTO(Payroll payroll);
    Payroll toEntity(PayrollDTO payrollDTO);
    List<PayrollDTO> toDTOs(List<Payroll> payrolls);
    List<Payroll> toEntities(List<PayrollDTO> payrollDTOs);
}
