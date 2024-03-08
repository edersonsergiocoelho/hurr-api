package br.com.escconsulting.service.mercado.pago.impl;

import br.com.escconsulting.config.MPConfig;
import br.com.escconsulting.dto.mercado.pago.MercadoPagoPreferenceRequestDTO;
import br.com.escconsulting.service.mercado.pago.MPPreferenceService;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.AddressRequest;
import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MPPreferenceServiceImpl implements MPPreferenceService {

    private final MPConfig mpConfig;

    @Override
    public Optional<Preference> findById(String preferenceId) throws MPException, MPApiException {
        PreferenceClient preferenceClient = new PreferenceClient();
        return Optional.ofNullable(preferenceClient.get(preferenceId));
    }

    @Override
    public Preference createPreference(MercadoPagoPreferenceRequestDTO preferenceRequestDTO) throws MPException, MPApiException {

        MercadoPagoConfig.setAccessToken(mpConfig.getAccessToken());

        PreferenceClient preferenceClient = new PreferenceClient();

        List<PreferenceItemRequest> items = new ArrayList<>();

        PreferenceItemRequest item = PreferenceItemRequest.builder()
                .title(preferenceRequestDTO.getItems().getFirst().getTitle())
                .quantity(preferenceRequestDTO.getItems().getFirst().getQuantity())
                .unitPrice(preferenceRequestDTO.getItems().getFirst().getUnitPrice())
                .build();

        items.add(item);

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .payer(PreferencePayerRequest.builder()
                        .address(AddressRequest.builder()
                                .streetName(preferenceRequestDTO.getPayer().getAddress().getStreetName())
                                .streetNumber(preferenceRequestDTO.getPayer().getAddress().getStreetNumber())
                                .zipCode(preferenceRequestDTO.getPayer().getAddress().getZipCode())
                                .build())
                        .build())
                .notificationUrl(preferenceRequestDTO.getNotificationUrl())
                .backUrls(PreferenceBackUrlsRequest.builder()
                        .success(preferenceRequestDTO.getBackUrls().getSuccess())
                        .build())
                .metadata(preferenceRequestDTO.getMetadata())
                .items(items).build();

        Preference preference = preferenceClient.create(preferenceRequest);

        preferenceRequest.getMetadata().put("preferenceId", preference.getId());

        preferenceClient.update(preference.getId(), preferenceRequest);

        return preference;
    }
}