package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.customer.vehicle.approved.CustomerVehicleApprovedDTO;
import br.com.escconsulting.entity.CustomerVehicleApproved;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerVehicleApprovedMapper {

    CustomerVehicleApprovedMapper INSTANCE = Mappers.getMapper(CustomerVehicleApprovedMapper.class);

    @Mapping(source = "customerVehicleApproved.customerVehicle", target = "customerVehicle")
    CustomerVehicleApprovedDTO toDTO(CustomerVehicleApproved customerVehicleApproved);

    void update(CustomerVehicleApproved source, @MappingTarget CustomerVehicleApproved target);
}