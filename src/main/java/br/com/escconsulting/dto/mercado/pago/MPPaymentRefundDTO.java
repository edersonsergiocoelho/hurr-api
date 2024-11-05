package br.com.escconsulting.dto.mercado.pago;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import com.mercadopago.resources.common.Source;
import com.mercadopago.resources.payment.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MPPaymentRefundDTO {

    private Long id;
    private Long paymentId;
    private BigDecimal amount;
    private BigDecimal adjustmentAmount;
    private String status;
    private String refundMode;
    private OffsetDateTime dateCreated;
    private String reason;
    private String uniqueSequenceNumber;
    private Source source;
}