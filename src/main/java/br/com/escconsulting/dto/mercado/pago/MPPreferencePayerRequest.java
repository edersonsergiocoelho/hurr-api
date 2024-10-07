package br.com.escconsulting.dto.mercado.pago;

import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.common.PhoneRequest;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class MPPreferencePayerRequest {

    private final String name;
    private final String surname;
    private final String email;
    private final PhoneRequest phone;
    private final IdentificationRequest identification;
    private final MPAddressRequest address;
    private final OffsetDateTime dateCreated;
}