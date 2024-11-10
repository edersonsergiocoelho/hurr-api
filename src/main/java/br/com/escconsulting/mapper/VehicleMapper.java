package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.vehicle.VehicleDTO;
import br.com.escconsulting.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehicleMapper {

    VehicleMapper INSTANCE = Mappers.getMapper( VehicleMapper.class );

    @Mapping(source = "vehicleBrand.file", target = "vehicleBrand.file")
    VehicleDTO toDTO(Vehicle vehicle);

    @Mapping(target = "vehicleBrand", ignore = true)
    VehicleDTO toDTOSimple(Vehicle vehicle);

    @Mapping(target = "vehicleBrand.file", ignore = true)
    VehicleDTO toDTONoFile(Vehicle vehicle);

    void update(Vehicle source, @MappingTarget Vehicle target);
}