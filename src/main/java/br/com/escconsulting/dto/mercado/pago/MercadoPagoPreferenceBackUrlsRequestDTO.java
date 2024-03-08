package br.com.escconsulting.dto.mercado.pago;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@JsonSerialize
public class MercadoPagoPreferenceBackUrlsRequestDTO {

    private final String success;
    private final String pending;
    private final String failure;
}