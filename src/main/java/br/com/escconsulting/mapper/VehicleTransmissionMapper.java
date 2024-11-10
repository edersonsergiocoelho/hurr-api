package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.vehicle.transmission.VehicleTransmissionDTO;
import br.com.escconsulting.entity.VehicleTransmission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehicleTransmissionMapper {

    VehicleTransmissionMapper INSTANCE = Mappers.getMapper( VehicleTransmissionMapper.class );

    @Mapping(source = "file", target = "file")
    VehicleTransmissionDTO toDTO(VehicleTransmission vehicleTransmission);

    @Mapping(target = "file", ignore = true)
    VehicleTransmissionDTO toDTOSimple(VehicleTransmission vehicleTransmission);

    void update(VehicleTransmission source, @MappingTarget VehicleTransmission target);
}