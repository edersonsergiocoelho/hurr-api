package br.com.escconsulting.dto.mercado.pago;

import com.mercadopago.client.preference.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@Data
public class MPPreferenceRequestDTO {

    private final String additionalInfo;
    private final String autoReturn;
    private final MPPreferenceBackUrlsRequest backUrls;
    private final Boolean binaryMode;
    private final OffsetDateTime dateOfExpiration;
    private final PreferenceDifferentialPricingRequest differentialPricing;
    private final OffsetDateTime expirationDateFrom;
    private final OffsetDateTime expirationDateTo;
    private final Boolean expires;
    private final String externalReference;
    private final List<MPPreferenceItemRequest> items;
    private final String marketplace;
    private final BigDecimal marketplaceFee;
    private final Map<String, Object> metadata;
    private final String notificationUrl;
    private final String operationType;
    private final MPPreferencePayerRequest payer;
    private final PreferencePaymentMethodsRequest paymentMethods;
    private final List<String> processingModes;
    private final String purpose;
    private final PreferenceShipmentsRequest shipments;
    private final String statementDescriptor;
    private final List<PreferenceTaxRequest> taxes;
    private final List<PreferenceTrackRequest> tracks;
}
