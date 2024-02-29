package br.com.escconsulting.dto.mercado.pago;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.common.PhoneRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@JsonSerialize
public class MercadoPagoPreferencePayerRequestDTO {

    private final String name;
    private final String surname;
    private final String email;
    private final PhoneRequest phone;
    private final IdentificationRequest identification;
    private final MercadoPagoAddressRequestDTO address;
    private final OffsetDateTime dateCreated;
}
