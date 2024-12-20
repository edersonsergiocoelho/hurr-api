package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.payment.method.PaymentMethodDTO;
import br.com.escconsulting.dto.payment.status.PaymentStatusDTO;
import br.com.escconsulting.entity.CustomerVehicleApproved;
import br.com.escconsulting.entity.PaymentMethod;
import br.com.escconsulting.entity.PaymentStatus;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentStatusMapper {
    
    PaymentStatusMapper INSTANCE = Mappers.getMapper( PaymentStatusMapper.class );

    PaymentStatusDTO toDTO(PaymentStatus paymentStatus);

    void update(PaymentStatus source, @MappingTarget PaymentStatus target);
}