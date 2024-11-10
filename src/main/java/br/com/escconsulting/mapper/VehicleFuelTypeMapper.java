package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.vehicle.fuel.type.VehicleFuelTypeDTO;
import br.com.escconsulting.entity.VehicleFuelType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehicleFuelTypeMapper {

    VehicleFuelTypeMapper INSTANCE = Mappers.getMapper(VehicleFuelTypeMapper.class);

    @Mapping(source = "file", target = "file")
    VehicleFuelTypeDTO toDTO(VehicleFuelType vehicleFuelType);

    @Mapping(target = "file", ignore = true)
    VehicleFuelTypeDTO toDTOSimple(VehicleFuelType vehicleFuelType);

    void update(VehicleFuelType source, @MappingTarget VehicleFuelType target);
}