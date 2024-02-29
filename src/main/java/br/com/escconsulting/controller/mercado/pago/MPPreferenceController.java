package br.com.escconsulting.controller.mercado.pago;

import br.com.escconsulting.config.MPConfig;
import br.com.escconsulting.dto.mercado.pago.MercadoPagoPreferenceRequestDTO;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.AddressRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferencePayerRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mercado-pago/create-preference")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MPPreferenceController {

    private final MPConfig mpConfig;

    @PostMapping
    public Preference create(@RequestBody MercadoPagoPreferenceRequestDTO mercadoPagoPreferenceRequestDTO) throws MPException, MPApiException {

        MercadoPagoConfig.setAccessToken(mpConfig.getAccessToken());

        PreferenceClient preferenceClient = new PreferenceClient();

        List<PreferenceItemRequest> items = new ArrayList<>();

        PreferenceItemRequest item = PreferenceItemRequest.builder()
                        .title(mercadoPagoPreferenceRequestDTO.getItems().getFirst().getTitle())
                        .quantity(mercadoPagoPreferenceRequestDTO.getItems().getFirst().getQuantity())
                        .unitPrice(mercadoPagoPreferenceRequestDTO.getItems().getFirst().getUnitPrice())
                        .build();

        items.add(item);

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .payer(PreferencePayerRequest.builder()
                        .address(AddressRequest.builder()
                                .streetName(mercadoPagoPreferenceRequestDTO.getPayer().getAddress().getStreetName())
                                .streetNumber(mercadoPagoPreferenceRequestDTO.getPayer().getAddress().getStreetNumber())
                                .zipCode(mercadoPagoPreferenceRequestDTO.getPayer().getAddress().getZipCode())
                                .build())
                        .build())
                .notificationUrl(mercadoPagoPreferenceRequestDTO.getNotificationUrl())
                .externalReference("")
                .items(items).build();

        return preferenceClient.create(preferenceRequest);
    }
}