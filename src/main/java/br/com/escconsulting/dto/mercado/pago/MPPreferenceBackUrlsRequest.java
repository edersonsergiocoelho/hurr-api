package br.com.escconsulting.dto.mercado.pago;

import lombok.Data;

@Data
public class MPPreferenceBackUrlsRequest {

    private final String success;
    private final String pending;
    private final String failure;
}