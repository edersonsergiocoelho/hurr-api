package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.customer.vehicle.CustomerVehicleDTO;
import br.com.escconsulting.entity.CustomerVehicle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerVehicleMapper {

    CustomerVehicleMapper INSTANCE = Mappers.getMapper( CustomerVehicleMapper.class );

    CustomerVehicleDTO toDTO(CustomerVehicle customerVehicle);
}