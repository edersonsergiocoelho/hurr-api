package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.customer.vehicle.withdrawal.request.CustomerVehicleWithdrawalRequestDTO;
import br.com.escconsulting.entity.CustomerVehicleWithdrawalRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerVehicleWithdrawalRequestMapper {

    CustomerVehicleWithdrawalRequestMapper INSTANCE = Mappers.getMapper(CustomerVehicleWithdrawalRequestMapper.class);

    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "customerVehicleBooking", target = "customerVehicleBooking")
    @Mapping(source = "customerVehicleBankAccount", target = "customerVehicleBankAccount")
    @Mapping(source = "paymentMethod", target = "paymentMethod")
    @Mapping(source = "paymentStatus", target = "paymentStatus")
    CustomerVehicleWithdrawalRequestDTO toDTO(CustomerVehicleWithdrawalRequest customerVehicleWithdrawalRequest);

    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "customerVehicleBooking", target = "customerVehicleBooking")
    @Mapping(source = "customerVehicleBooking.customer", target = "customerVehicleBooking.customer", ignore = true)
    @Mapping(source = "customerVehicleBooking.customerVehicle", target = "customerVehicleBooking.customerVehicle", ignore = true)
    @Mapping(source = "customerVehicleBooking.customerAddressBilling", target = "customerVehicleBooking.customerAddressBilling", ignore = true)
    @Mapping(source = "customerVehicleBooking.customerAddressDelivery", target = "customerVehicleBooking.customerAddressDelivery", ignore = true)
    @Mapping(source = "customerVehicleBooking.customerAddressPickUp", target = "customerVehicleBooking.customerAddressPickUp", ignore = true)
    @Mapping(source = "customerVehicleBankAccount", target = "customerVehicleBankAccount")
    @Mapping(source = "paymentMethod", target = "paymentMethod")
    @Mapping(source = "paymentStatus", target = "paymentStatus")
    CustomerVehicleWithdrawalRequestDTO toDTOCustomerVehicleBookingNoFK(CustomerVehicleWithdrawalRequest customerVehicleWithdrawalRequest);

    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "customerVehicleBooking", ignore = true)
    @Mapping(target = "customerVehicleBankAccount", ignore = true)
    @Mapping(target = "paymentMethod", ignore = true)
    @Mapping(target = "paymentStatus", ignore = true)
    CustomerVehicleWithdrawalRequestDTO toDTOSimple(CustomerVehicleWithdrawalRequest customerVehicleWithdrawalRequest);

    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "customerVehicleBooking", target = "customerVehicleBooking")
    @Mapping(source = "customerVehicleBankAccount", target = "customerVehicleBankAccount")
    @Mapping(source = "paymentMethod", target = "paymentMethod")
    @Mapping(target = "paymentMethod.file", ignore = true)
    @Mapping(source = "paymentStatus", target = "paymentStatus")
    @Mapping(target = "paymentStatus.file", ignore = true)
    CustomerVehicleWithdrawalRequestDTO toDTONoFile(CustomerVehicleWithdrawalRequest customerVehicleWithdrawalRequest);
}