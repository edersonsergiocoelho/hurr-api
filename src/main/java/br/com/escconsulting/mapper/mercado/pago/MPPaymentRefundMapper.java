package br.com.escconsulting.mapper.mercado.pago;

import br.com.escconsulting.dto.mercado.pago.MPPaymentRefundDTO;
import com.mercadopago.resources.payment.PaymentRefund;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MPPaymentRefundMapper {

    MPPaymentRefundMapper INSTANCE = Mappers.getMapper( MPPaymentRefundMapper.class );

    void update(PaymentRefund source, @MappingTarget MPPaymentRefundDTO target);
}