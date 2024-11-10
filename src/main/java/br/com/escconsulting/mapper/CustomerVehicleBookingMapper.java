package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.customer.vehicle.booking.CustomerVehicleBookingDTO;
import br.com.escconsulting.entity.CustomerVehicleBankAccount;
import br.com.escconsulting.entity.CustomerVehicleBooking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerVehicleBookingMapper {

    CustomerVehicleBookingMapper INSTANCE = Mappers.getMapper( CustomerVehicleBookingMapper.class );

    @Mapping(source = "customerVehicle", target = "customerVehicle")
    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "customerAddressBilling", target = "customerAddressBilling")
    @Mapping(source = "customerAddressDelivery", target = "customerAddressDelivery")
    @Mapping(source = "customerAddressPickUp", target = "customerAddressPickUp")
    @Mapping(source = "mpPayment", target = "mpPayment")
    @Mapping(source = "mpPaymentRefund", target = "mpPaymentRefund")
    @Mapping(source = "mpPaymentAdditional", target = "mpPaymentAdditional")
    CustomerVehicleBookingDTO toDTO(CustomerVehicleBooking customerVehicleBooking);

    @Mapping(target = "customerVehicle", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "customerAddressBilling", ignore = true)
    @Mapping(target = "customerAddressDelivery", ignore = true)
    @Mapping(target = "customerAddressPickUp", ignore = true)
    @Mapping(target = "mpPayment", ignore = true)
    @Mapping(target = "mpPaymentRefund", ignore = true)
    @Mapping(target = "mpPaymentAdditional", ignore = true)
    CustomerVehicleBookingDTO toDTOSimple(CustomerVehicleBooking entity);

    @Mapping(target = "customerVehicle.vehicle.vehicleBrand.file", ignore = true)
    @Mapping(target = "customerVehicle.vehicleFuelType.file", ignore = true)
    @Mapping(target = "customerVehicle.vehicleTransmission.file", ignore = true)
    CustomerVehicleBookingDTO toDTONoFile(CustomerVehicleBooking customerVehicleBooking);

    void update(CustomerVehicleBooking source, @MappingTarget CustomerVehicleBooking target);
}