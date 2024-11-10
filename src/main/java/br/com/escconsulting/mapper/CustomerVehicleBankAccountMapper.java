package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.customer.vehicle.bank.account.CustomerVehicleBankAccountDTO;
import br.com.escconsulting.entity.CustomerVehicleBankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerVehicleBankAccountMapper {
    
    CustomerVehicleBankAccountMapper INSTANCE = Mappers.getMapper( CustomerVehicleBankAccountMapper.class );

    @Mapping(source = "customerVehicleBankAccount.customer", target = "customer")
    @Mapping(source = "customerVehicleBankAccount.bank", target = "bank")
    CustomerVehicleBankAccountDTO toDTO(CustomerVehicleBankAccount customerVehicleBankAccount);

    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "bank", ignore = true)
    CustomerVehicleBankAccountDTO toDTOSimple(CustomerVehicleBankAccount customerVehicleBankAccount);

    void update(CustomerVehicleBankAccount source, @MappingTarget CustomerVehicleBankAccount target);
}