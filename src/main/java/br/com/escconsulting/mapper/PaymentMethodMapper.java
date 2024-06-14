package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.payment.method.PaymentMethodDTO;
import br.com.escconsulting.entity.PaymentMethod;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMethodMapper {
    
    PaymentMethodMapper INSTANCE = Mappers.getMapper( PaymentMethodMapper.class );

    PaymentMethodDTO toDTO(PaymentMethod paymentMethod);
}