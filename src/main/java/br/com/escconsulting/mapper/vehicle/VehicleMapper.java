package br.com.escconsulting.mapper.vehicle;

import br.com.escconsulting.dto.vehicle.VehicleDTO;
import br.com.escconsulting.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehicleMapper {

    VehicleMapper INSTANCE = Mappers.getMapper( VehicleMapper.class );

    VehicleDTO toDTO(Vehicle vehicle);
}