package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.customer.vehicle.file.insurance.CustomerVehicleFileInsuranceDTO;
import br.com.escconsulting.entity.CustomerVehicleFileInsurance;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerVehicleFileInsuranceMapper {

    CustomerVehicleFileInsuranceMapper INSTANCE = Mappers.getMapper(CustomerVehicleFileInsuranceMapper.class);

    CustomerVehicleFileInsuranceDTO toDTO(CustomerVehicleFileInsurance customerVehicleFileInsurance);

    void update(CustomerVehicleFileInsurance source, @MappingTarget CustomerVehicleFileInsurance target);
}