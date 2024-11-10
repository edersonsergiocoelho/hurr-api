package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.customer.vehicle.address.CustomerVehicleAddressDTO;
import br.com.escconsulting.entity.CustomerVehicleAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerVehicleAddressMapper {

    CustomerVehicleAddressMapper INSTANCE = Mappers.getMapper(CustomerVehicleAddressMapper.class);

    @Mapping(source = "address", target = "address")
    CustomerVehicleAddressDTO toDTO(CustomerVehicleAddress customerVehicleAddress);

    @Mapping(target = "address", ignore = true)
    CustomerVehicleAddressDTO toDTOSimple(CustomerVehicleAddress customerVehicleAddress);

    void update(CustomerVehicleAddress source, @MappingTarget CustomerVehicleAddress target);
}