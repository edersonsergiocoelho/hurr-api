package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.customer.vehicle.file.photo.CustomerVehicleFilePhotoDTO;
import br.com.escconsulting.entity.CustomerVehicleFilePhoto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerVehicleFilePhotoMapper {

    CustomerVehicleFilePhotoMapper INSTANCE = Mappers.getMapper(CustomerVehicleFilePhotoMapper.class);

    CustomerVehicleFilePhotoDTO toDTO(CustomerVehicleFilePhoto customerVehicleFilePhoto);

    void update(CustomerVehicleFilePhotoDTO source, @MappingTarget CustomerVehicleFilePhoto target);
}