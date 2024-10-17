package br.com.escconsulting.dto.inter;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class InterIncluirPIXDTO {

    private UUID customerVehicleWithdrawalRequestId;
    private String chave;
    private BigDecimal valor;
}