package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.customer.vehicle.CustomerVehicleDTO;
import br.com.escconsulting.entity.CustomerVehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerVehicleMapper {

    CustomerVehicleMapper INSTANCE = Mappers.getMapper( CustomerVehicleMapper.class );

    @Mapping(source = "customerVehicle.vehicleModel.vehicle", target = "vehicleModel.vehicle", ignore = true)
    CustomerVehicleDTO toDTO(CustomerVehicle customerVehicle);

    void update(CustomerVehicle source, @MappingTarget CustomerVehicle target);
}