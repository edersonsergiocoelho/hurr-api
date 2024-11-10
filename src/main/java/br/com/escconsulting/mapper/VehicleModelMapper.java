package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.vehicle.model.VehicleModelDTO;
import br.com.escconsulting.entity.VehicleModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehicleModelMapper {

    VehicleModelMapper INSTANCE = Mappers.getMapper( VehicleModelMapper.class );

    @Mapping(source = "vehicle", target = "vehicle")
    @Mapping(source = "vehicleCategory", target = "vehicleCategory")
    VehicleModelDTO toDTO(VehicleModel vehicleModel);

    @Mapping(source = "vehicle", target = "vehicle", ignore = true)
    @Mapping(source = "vehicleCategory", target = "vehicleCategory", ignore = true)
    VehicleModelDTO toDTOSimple(VehicleModel vehicleModel);

    @Mapping(source = "vehicle.vehicleBrand.file", target = "vehicle.vehicleBrand.file", ignore = true)
    @Mapping(source = "vehicleCategory.file", target = "vehicleCategory.file", ignore = true)
    VehicleModelDTO toDTONoFile(VehicleModel vehicleModel);

    void update(VehicleModel source, @MappingTarget VehicleModel target);
}