package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.customer.withdrawal.request.CustomerWithdrawalRequestDTO;
import br.com.escconsulting.entity.CustomerWithdrawalRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerWithdrawalRequestMapper {

    CustomerWithdrawalRequestMapper INSTANCE = Mappers.getMapper(CustomerWithdrawalRequestMapper.class);

    @Mapping(source = "customerWithdrawalRequest.customer", target = "customer")
    @Mapping(source = "customerWithdrawalRequest.customerVehicleBooking", target = "customerVehicleBooking")
    @Mapping(source = "customerWithdrawalRequest.customerVehicleBooking.customerVehicle", target = "customerVehicleBooking.customerVehicle", ignore = true)
    @Mapping(source = "customerWithdrawalRequest.customerVehicleBooking.customer", target = "customerVehicleBooking.customer", ignore = true)
    @Mapping(source = "customerWithdrawalRequest.customerVehicleBooking.customerAddressDelivery", target = "customerVehicleBooking.customerAddressDelivery", ignore = true)
    @Mapping(source = "customerWithdrawalRequest.customerVehicleBooking.customerAddressPickUp", target = "customerVehicleBooking.customerAddressPickUp", ignore = true)
    @Mapping(source = "customerWithdrawalRequest.customerBankAccount", target = "customerBankAccount")
    @Mapping(source = "customerWithdrawalRequest.paymentMethod", target = "paymentMethod")
    @Mapping(source = "customerWithdrawalRequest.paymentStatus", target = "paymentStatus")
    CustomerWithdrawalRequestDTO toDTO(CustomerWithdrawalRequest customerWithdrawalRequest);

    @Mapping(source = "customerWithdrawalRequest.customer", target = "customer")
    @Mapping(source = "customerWithdrawalRequest.customerVehicleBooking", target = "customerVehicleBooking")
    @Mapping(source = "customerWithdrawalRequest.customerVehicleBooking.customerVehicle", target = "customerVehicleBooking.customerVehicle", ignore = true)
    @Mapping(source = "customerWithdrawalRequest.customerVehicleBooking.customer", target = "customerVehicleBooking.customer", ignore = true)
    @Mapping(source = "customerWithdrawalRequest.customerVehicleBooking.customerAddressDelivery", target = "customerVehicleBooking.customerAddressDelivery", ignore = true)
    @Mapping(source = "customerWithdrawalRequest.customerVehicleBooking.customerAddressPickUp", target = "customerVehicleBooking.customerAddressPickUp", ignore = true)
    @Mapping(source = "customerWithdrawalRequest.customerBankAccount", target = "customerBankAccount")
    @Mapping(source = "customerWithdrawalRequest.paymentMethod", target = "paymentMethod")
    @Mapping(source = "customerWithdrawalRequest.paymentMethod.file", target = "paymentMethod.file", ignore = true)
    @Mapping(source = "customerWithdrawalRequest.paymentStatus", target = "paymentStatus")
    CustomerWithdrawalRequestDTO toDTONoFile(CustomerWithdrawalRequest customerWithdrawalRequest);
}