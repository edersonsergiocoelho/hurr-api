package br.com.escconsulting.dto.fee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeeDTO  {

    private UUID feeId;
    private String feeType;
    private BigDecimal amount;
    private Instant startDate;
    private Instant endDate;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}