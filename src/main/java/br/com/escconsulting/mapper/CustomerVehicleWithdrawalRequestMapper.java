package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.customer.vehicle.withdrawal.request.CustomerVehicleWithdrawalRequestDTO;
import br.com.escconsulting.entity.CustomerVehicleWithdrawalRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerVehicleWithdrawalRequestMapper {

    CustomerVehicleWithdrawalRequestMapper INSTANCE = Mappers.getMapper(CustomerVehicleWithdrawalRequestMapper.class);

    @Mapping(source = "customerVehicleWithdrawalRequest.customer", target = "customer")
    @Mapping(source = "customerVehicleWithdrawalRequest.customerVehicleBooking", target = "customerVehicleBooking")
    @Mapping(source = "customerVehicleWithdrawalRequest.customerVehicleBooking.customerVehicle", target = "customerVehicleBooking.customerVehicle", ignore = true)
    @Mapping(source = "customerVehicleWithdrawalRequest.customerVehicleBooking.customer", target = "customerVehicleBooking.customer", ignore = true)
    @Mapping(source = "customerVehicleWithdrawalRequest.customerVehicleBooking.customerAddressDelivery", target = "customerVehicleBooking.customerAddressDelivery", ignore = true)
    @Mapping(source = "customerVehicleWithdrawalRequest.customerVehicleBooking.customerAddressPickUp", target = "customerVehicleBooking.customerAddressPickUp", ignore = true)
    @Mapping(source = "customerVehicleWithdrawalRequest.customerVehicleBankAccount", target = "customerVehicleBankAccount")
    @Mapping(source = "customerVehicleWithdrawalRequest.paymentMethod", target = "paymentMethod")
    @Mapping(source = "customerVehicleWithdrawalRequest.paymentStatus", target = "paymentStatus")
    CustomerVehicleWithdrawalRequestDTO toDTO(CustomerVehicleWithdrawalRequest customerVehicleWithdrawalRequest);

    @Mapping(source = "customerVehicleWithdrawalRequest.customer", target = "customer")
    @Mapping(source = "customerVehicleWithdrawalRequest.customerVehicleBooking", target = "customerVehicleBooking")
    @Mapping(source = "customerVehicleWithdrawalRequest.customerVehicleBooking.customerVehicle", target = "customerVehicleBooking.customerVehicle", ignore = true)
    @Mapping(source = "customerVehicleWithdrawalRequest.customerVehicleBooking.customer", target = "customerVehicleBooking.customer", ignore = true)
    @Mapping(source = "customerVehicleWithdrawalRequest.customerVehicleBooking.customerAddressDelivery", target = "customerVehicleBooking.customerAddressDelivery", ignore = true)
    @Mapping(source = "customerVehicleWithdrawalRequest.customerVehicleBooking.customerAddressPickUp", target = "customerVehicleBooking.customerAddressPickUp", ignore = true)
    @Mapping(source = "customerVehicleWithdrawalRequest.customerVehicleBankAccount", target = "customerVehicleBankAccount")
    @Mapping(source = "customerVehicleWithdrawalRequest.paymentMethod", target = "paymentMethod")
    @Mapping(source = "customerVehicleWithdrawalRequest.paymentMethod.file", target = "paymentMethod.file", ignore = true)
    @Mapping(source = "customerVehicleWithdrawalRequest.paymentStatus", target = "paymentStatus")
    CustomerVehicleWithdrawalRequestDTO toDTONoFile(CustomerVehicleWithdrawalRequest customerVehicleWithdrawalRequest);
}