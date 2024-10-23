package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.vehicle.VehicleDTO;
import br.com.escconsulting.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehicleMapper {

    VehicleMapper INSTANCE = Mappers.getMapper( VehicleMapper.class );

    VehicleDTO toDTO(Vehicle vehicle);

    @Mapping(source = "vehicle.vehicleBrand.file", target = "vehicleBrand.file", ignore = true)
    VehicleDTO toDTONoFile(Vehicle vehicle);
}