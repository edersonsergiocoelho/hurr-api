package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.vehicle.color.VehicleColorDTO;
import br.com.escconsulting.entity.VehicleColor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehicleColorMapper {

    VehicleColorMapper INSTANCE = Mappers.getMapper(VehicleColorMapper.class);

    VehicleColorDTO toDTO(VehicleColor vehicleColor);
}