package br.com.escconsulting.dto.mercado.pago;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import com.mercadopago.resources.payment.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MPPaymentDTO {

    private Long id;
    private OffsetDateTime dateCreated;
    private OffsetDateTime dateApproved;
    private OffsetDateTime dateLastUpdated;
    private OffsetDateTime dateOfExpiration;
    private OffsetDateTime moneyReleaseDate;
    private String moneyReleaseSchema;
    private String operationType;
    private String issuerId;
    private String paymentMethodId;
    private String paymentTypeId;
    private String status;
    private String statusDetail;
    private String currencyId;
    private String description;
    private boolean liveMode;
    private Long sponsorId;
    private String authorizationCode;
    private String integratorId;
    private String platformId;
    private String corporationId;
    private Long collectorId;
    private PaymentPayer payer;
    private Map<String, Object> metadata;
    private PaymentAdditionalInfo additionalInfo;
    private PaymentOrder order;
    private String externalReference;
    private BigDecimal transactionAmount;
    private BigDecimal transactionAmountRefunded;
    private BigDecimal couponAmount;
    private String differentialPricingId;
    private int installments;
    private PaymentTransactionDetails transactionDetails;
    private List<PaymentFeeDetail> feeDetails;
    private boolean captured;
    private boolean binaryMode;
    private String callForAuthorizeId;
    private String statementDescriptor;
    private PaymentCard card;
    private String notificationUrl;
    private String callbackUrl;
    private String processingMode;
    private String merchantAccountId;
    private String merchantNumber;
    private String couponCode;
    private BigDecimal netAmount;
    private String paymentMethodOptionId;
    private List<PaymentTax> taxes;
    private BigDecimal taxesAmount;
    private String counterCurrency;
    private BigDecimal shippingAmount;
    private String posId;
    private String storeId;
    private String deductionSchema;
    private List<PaymentRefund> refunds;
    private PaymentPointOfInteraction pointOfInteraction;
    private PaymentMethod paymentMethod;
    @SerializedName("three_ds_info")
    private PaymentThreeDSInfo threeDSInfo;
    private Map<String, Object> internalMetadata;
}