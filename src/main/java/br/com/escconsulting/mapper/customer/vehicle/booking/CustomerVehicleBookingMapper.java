package br.com.escconsulting.mapper.customer.vehicle.booking;

import br.com.escconsulting.dto.customer.vehicle.booking.CustomerVehicleBookingDTO;
import br.com.escconsulting.entity.CustomerVehicleBooking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerVehicleBookingMapper {

    CustomerVehicleBookingMapper INSTANCE = Mappers.getMapper( CustomerVehicleBookingMapper.class );

    @Mapping(source = "customerVehicleBooking.customer", target = "customer")
    @Mapping(source = "customerVehicleBooking.customerVehicle", target = "customerVehicle")
    @Mapping(source = "customerVehicleBooking.customerVehicle.vehicle", target = "customerVehicle.vehicle")
    @Mapping(source = "customerVehicleBooking.customerVehicle.vehicle.vehicleBrand", target = "customerVehicle.vehicle.vehicleBrand")
    @Mapping(source = "customerVehicleBooking.customerVehicle.vehicleModel", target = "customerVehicle.vehicleModel")
    @Mapping(source = "customerVehicleBooking.customerVehicle.vehicleModel.vehicleCategory", target = "customerVehicle.vehicleModel.vehicleCategory")
    @Mapping(source = "customerVehicleBooking.customerVehicle.vehicleColor", target = "customerVehicle.vehicleColor")
    @Mapping(source = "customerVehicleBooking.customerVehicle.vehicleFuelType", target = "customerVehicle.vehicleFuelType")
    @Mapping(source = "customerVehicleBooking.customerVehicle.vehicleTransmission", target = "customerVehicle.vehicleTransmission")
    @Mapping(source = "customerVehicleBooking.customerAddressDelivery", target = "customerAddressDelivery")
    @Mapping(source = "customerVehicleBooking.customerAddressPickUp", target = "customerAddressPickUp")
    CustomerVehicleBookingDTO toDTO(CustomerVehicleBooking customerVehicleBooking);
}