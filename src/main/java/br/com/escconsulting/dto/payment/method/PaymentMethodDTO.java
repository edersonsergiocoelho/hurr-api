package br.com.escconsulting.dto.payment.method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodDTO {
    
    private UUID paymentMethodId;
    private String paymentMethodName;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}