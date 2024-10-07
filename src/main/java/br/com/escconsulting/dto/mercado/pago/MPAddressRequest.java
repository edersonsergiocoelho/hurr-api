package br.com.escconsulting.dto.mercado.pago;

import lombok.Data;

@Data
public class MPAddressRequest {

    private final String zipCode;
    private final String streetName;
    private final String streetNumber;
}