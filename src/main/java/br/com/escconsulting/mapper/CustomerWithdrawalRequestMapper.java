package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.customer.withdrawal.request.CustomerWithdrawalRequestDTO;
import br.com.escconsulting.entity.CustomerWithdrawalRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerWithdrawalRequestMapper {

    CustomerWithdrawalRequestMapper INSTANCE = Mappers.getMapper( CustomerWithdrawalRequestMapper.class );

    @Mapping(source = "customerWithdrawalRequest.paymentMethod", target = "paymentMethod")
    CustomerWithdrawalRequestDTO toDTO(CustomerWithdrawalRequest customerWithdrawalRequest);
}