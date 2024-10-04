package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.customer.vehicle.booking.CustomerVehicleBookingDTO;
import br.com.escconsulting.entity.CustomerVehicleBooking;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerVehicleBookingMapper {

    CustomerVehicleBookingMapper INSTANCE = Mappers.getMapper( CustomerVehicleBookingMapper.class );

    CustomerVehicleBookingDTO toDTO(CustomerVehicleBooking customerVehicleBooking);

    void update(CustomerVehicleBooking customerVehicleBooking, @MappingTarget CustomerVehicleBooking existingCustomerVehicleBooking);
}