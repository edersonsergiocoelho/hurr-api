package br.com.escconsulting.dto.payment.status;

import br.com.escconsulting.dto.file.FileDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentStatusDTO {

    private UUID paymentStatusId;
    private String paymentStatusName;
    private FileDTO file;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}