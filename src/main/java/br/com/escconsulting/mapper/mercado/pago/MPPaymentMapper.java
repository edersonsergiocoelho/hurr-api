package br.com.escconsulting.mapper.mercado.pago;

import br.com.escconsulting.dto.mercado.pago.MPPaymentDTO;
import com.mercadopago.resources.payment.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MPPaymentMapper {

    MPPaymentMapper INSTANCE = Mappers.getMapper( MPPaymentMapper.class );

    void update(Payment source, @MappingTarget MPPaymentDTO target);
}