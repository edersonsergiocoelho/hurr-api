package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.vehicle.model.VehicleModelDTO;
import br.com.escconsulting.entity.Vehicle;
import br.com.escconsulting.entity.VehicleModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehicleModelMapper {

    VehicleModelMapper INSTANCE = Mappers.getMapper( VehicleModelMapper.class );

    VehicleModelDTO toDTO(Vehicle vehicle);

    @Mapping(source = "vehicleModel.vehicle.vehicleBrand.file", target = "vehicle.vehicleBrand.file", ignore = true)
    VehicleModelDTO toDTONoFile(VehicleModel vehicleModel);
}