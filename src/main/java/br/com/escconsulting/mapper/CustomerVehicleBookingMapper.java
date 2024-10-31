package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.customer.vehicle.booking.CustomerVehicleBookingDTO;
import br.com.escconsulting.entity.CustomerVehicleBooking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerVehicleBookingMapper {

    CustomerVehicleBookingMapper INSTANCE = Mappers.getMapper( CustomerVehicleBookingMapper.class );

    @Mapping(source = "customerVehicle.vehicle.vehicleBrand.file", target = "customerVehicle.vehicle.vehicleBrand.file")
    @Mapping(source = "customerVehicle.vehicleModel.vehicle", target = "customerVehicle.vehicleModel.vehicle")
    CustomerVehicleBookingDTO toDTO(CustomerVehicleBooking customerVehicleBooking);

    @Mapping(source = "customerVehicle.vehicle.vehicleBrand.file", target = "customerVehicle.vehicle.vehicleBrand.file", ignore = true)
    @Mapping(source = "customerVehicle.vehicleModel.vehicle", target = "customerVehicle.vehicleModel.vehicle", ignore = true)
    CustomerVehicleBookingDTO toDTONoFile(CustomerVehicleBooking customerVehicleBooking);

    void update(CustomerVehicleBooking customerVehicleBooking, @MappingTarget CustomerVehicleBooking existingCustomerVehicleBooking);
}