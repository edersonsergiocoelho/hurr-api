package br.com.escconsulting.dto.mercado.pago;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mercadopago.client.preference.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@JsonSerialize
public class MPPreferenceRequestDTO {

    private final String additionalInfo;
    private final String autoReturn;
    private final PreferenceBackUrlsRequest backUrls;
    private final Boolean binaryMode;
    private final OffsetDateTime dateOfExpiration;
    private final PreferenceDifferentialPricingRequest differentialPricing;
    private final OffsetDateTime expirationDateFrom;
    private final OffsetDateTime expirationDateTo;
    private final Boolean expires;
    private final String externalReference;
    private final List<PreferenceItemRequest> items;
    private final String marketplace;
    private final BigDecimal marketplaceFee;
    private final Map<String, Object> metadata;
    private final String notificationUrl;
    private final String operationType;
    private final PreferencePayerRequest payer;
    private final PreferencePaymentMethodsRequest paymentMethods;
    private final List<String> processingModes;
    private final String purpose;
    private final PreferenceShipmentsRequest shipments;
    private final String statementDescriptor;
    private final List<PreferenceTaxRequest> taxes;
    private final List<PreferenceTrackRequest> tracks;
}
