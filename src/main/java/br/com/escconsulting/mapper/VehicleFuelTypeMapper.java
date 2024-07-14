package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.vehicle.fuel.type.VehicleFuelTypeDTO;
import br.com.escconsulting.entity.VehicleFuelType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehicleFuelTypeMapper {

    VehicleFuelTypeMapper INSTANCE = Mappers.getMapper(VehicleFuelTypeMapper.class);

    VehicleFuelTypeDTO toDTO(VehicleFuelType vehicleFuelType);
}