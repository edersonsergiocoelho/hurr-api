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

    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "vehicle", target = "vehicle")
    @Mapping(source = "vehicleModel", target = "vehicleModel")
    @Mapping(source = "vehicleColor", target = "vehicleColor")
    @Mapping(source = "vehicleFuelType", target = "vehicleFuelType")
    @Mapping(source = "vehicleTransmission", target = "vehicleTransmission")
    @Mapping(source = "renavamState", target = "renavamState")
    CustomerVehicleDTO toDTO(CustomerVehicle customerVehicle);

    @Mapping(source = "customer", target = "customer", ignore = true)
    @Mapping(source = "vehicle", target = "vehicle", ignore = true)
    @Mapping(source = "vehicleModel", target = "vehicleModel", ignore = true)
    @Mapping(source = "vehicleColor", target = "vehicleColor", ignore = true)
    @Mapping(source = "vehicleFuelType", target = "vehicleFuelType", ignore = true)
    @Mapping(source = "vehicleTransmission", target = "vehicleTransmission", ignore = true)
    @Mapping(source = "renavamState", target = "renavamState", ignore = true)
    CustomerVehicleDTO toSimpleDTO(CustomerVehicle customerVehicle);

    void update(CustomerVehicle source, @MappingTarget CustomerVehicle target);
}