package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.customer.bank.account.CustomerBankAccountDTO;
import br.com.escconsulting.entity.CustomerBankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerBankAccountMapper {
    
    CustomerBankAccountMapper INSTANCE = Mappers.getMapper( CustomerBankAccountMapper.class );

    CustomerBankAccountDTO toDTO(CustomerBankAccount customerBankAccount);
}