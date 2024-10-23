package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.vehicle.brand.VehicleBrandDTO;
import br.com.escconsulting.entity.VehicleBrand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehicleBrandMapper {
    
    VehicleBrandMapper INSTANCE = Mappers.getMapper( VehicleBrandMapper.class );

    @Mapping(source = "vehicleBrand.file", target = "file")
    VehicleBrandDTO toDTO(VehicleBrand vehicleBrand);

    void update(VehicleBrand source, @MappingTarget VehicleBrand target);
}