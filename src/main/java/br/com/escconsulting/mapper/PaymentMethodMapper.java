package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.payment.method.PaymentMethodDTO;
import br.com.escconsulting.entity.PaymentMethod;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMethodMapper {
    
    PaymentMethodMapper INSTANCE = Mappers.getMapper( PaymentMethodMapper.class );

    @Mapping(source = "paymentMethod.file", target = "file")
    PaymentMethodDTO toDTO(PaymentMethod paymentMethod);
}