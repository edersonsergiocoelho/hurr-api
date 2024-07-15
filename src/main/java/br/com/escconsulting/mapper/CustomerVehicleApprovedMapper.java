package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.customer.vehicle.approved.CustomerVehicleApprovedDTO;
import br.com.escconsulting.entity.CustomerVehicleApproved;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerVehicleApprovedMapper {

    CustomerVehicleApprovedMapper INSTANCE = Mappers.getMapper(CustomerVehicleApprovedMapper.class);

    @Mapping(source = "customerVehicleApproved.customerVehicle", target = "customerVehicle")
    @Mapping(source = "customerVehicleApproved.customerVehicle.addresses", target = "customerVehicle.addresses", ignore = true)
    CustomerVehicleApprovedDTO toDTO(CustomerVehicleApproved customerVehicleApproved);
}