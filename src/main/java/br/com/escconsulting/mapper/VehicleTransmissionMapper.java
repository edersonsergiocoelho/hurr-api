package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.vehicle.transmission.VehicleTransmissionDTO;
import br.com.escconsulting.entity.VehicleTransmission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehicleTransmissionMapper {

    VehicleTransmissionMapper INSTANCE = Mappers.getMapper( VehicleTransmissionMapper.class );

    VehicleTransmissionDTO toDTO(VehicleTransmission vehicleTransmission);
}