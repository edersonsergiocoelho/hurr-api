package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.customer.vehicle.address.CustomerVehicleAddressDTO;
import br.com.escconsulting.entity.CustomerVehicleAddress;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerVehicleAddressMapper {

    CustomerVehicleAddressMapper INSTANCE = Mappers.getMapper(CustomerVehicleAddressMapper.class);

    CustomerVehicleAddressDTO toDTO(CustomerVehicleAddress customerVehicleAddress);
}