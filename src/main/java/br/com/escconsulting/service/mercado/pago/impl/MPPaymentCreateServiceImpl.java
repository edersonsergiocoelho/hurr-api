package br.com.escconsulting.service.mercado.pago.impl;

import br.com.escconsulting.config.MPConfig;
import br.com.escconsulting.service.mercado.pago.MPPaymentCreateService;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.cardtoken.CardTokenRequest;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.common.PhoneRequest;
import com.mercadopago.client.customer.CustomerCardClient;
import com.mercadopago.client.payment.*;
import com.mercadopago.client.paymentmethod.PaymentMethodClient;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.net.MPResourceList;
import com.mercadopago.resources.customer.CustomerCard;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.paymentmethod.PaymentMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MPPaymentCreateServiceImpl implements MPPaymentCreateService {

    private final MPConfig mpConfig;

    @Override
    public Payment create() throws MPException, MPApiException {

        Map<String, String> customHeaders = new HashMap<>();
        customHeaders.put("x-idempotency-key", UUID.randomUUID().toString());

        MPRequestOptions requestOptions = MPRequestOptions.builder()
                .customHeaders(customHeaders)
                .build();

        MercadoPagoConfig.setAccessToken(mpConfig.getAccessToken());

        PaymentClient client = new PaymentClient();

        List<PaymentItemRequest> items = new ArrayList<>();

        PaymentItemRequest item =
                PaymentItemRequest.builder()
                        .id("PR0001")
                        .title("Point Mini")
                        .description("Producto Point para cobros con tarjetas mediante bluetooth")
                        .pictureUrl(
                                "https://http2.mlstatic.com/resources/frontend/statics/growth-sellers-landings/device-mlb-point-i_medium@2x.png")
                        .categoryId("electronics")
                        .quantity(1)
                        .unitPrice(new BigDecimal("58.8"))
                        .build();

        items.add(item);

        PaymentCreateRequest createRequest =
                PaymentCreateRequest.builder()
                        .additionalInfo(
                                PaymentAdditionalInfoRequest.builder()
                                        .items(items)
                                        .payer(
                                                PaymentAdditionalInfoPayerRequest.builder()
                                                        .firstName("Test")
                                                        .lastName("Test")
                                                        .phone(
                                                                PhoneRequest.builder().areaCode("11").number("987654321").build())
                                                        .build())
                                        .shipments(
                                                PaymentShipmentsRequest.builder()
                                                        .receiverAddress(
                                                                PaymentReceiverAddressRequest.builder()
                                                                        .zipCode("12312-123")
                                                                        .stateName("Rio de Janeiro")
                                                                        .cityName("Buzios")
                                                                        .streetName("Av das Nacoes Unidas")
                                                                        .streetNumber("3003")
                                                                        .build())
                                                        .build())
                                        .build())
                        .description("Payment for product")
                        .externalReference("MP0001")
                        .installments(1)
                        .order(PaymentOrderRequest.builder().type("mercadolibre").build())
                        .payer(PaymentPayerRequest.builder().entityType("individual").type("customer").build())
                        .paymentMethodId("visa")
                        .transactionAmount(new BigDecimal("58.8"))
                        .build();

        return client.create(createRequest, requestOptions);
    }

    @Override
    public Payment createTest() throws MPException, MPApiException {

        try {

            Map<String, String> customHeaders = new HashMap<>();
            customHeaders.put("x-idempotency-key", UUID.randomUUID().toString());

            MPRequestOptions requestOptions = MPRequestOptions.builder()
                    .customHeaders(customHeaders)
                    .build();

            MercadoPagoConfig.setAccessToken(mpConfig.getAccessToken());

            PaymentClient client = new PaymentClient();

            List<PaymentItemRequest> items = new ArrayList<>();

            PaymentItemRequest item =
                    PaymentItemRequest.builder()
                            .id("PR0001")
                            .title("Point Mini")
                            .description("Producto Point para cobros con tarjetas mediante bluetooth")
                            .pictureUrl("https://http2.mlstatic.com/resources/frontend/statics/growth-sellers-landings/device-mlb-point-i_medium@2x.png")
                            .categoryId("electronics")
                            .quantity(1)
                            .unitPrice(new BigDecimal("58.8"))
                            .build();

            items.add(item);

            PaymentCreateRequest createRequest =
                    PaymentCreateRequest.builder()
                            .additionalInfo(
                                    PaymentAdditionalInfoRequest.builder()
                                            .items(items)
                                            .payer(
                                                    PaymentAdditionalInfoPayerRequest.builder()
                                                            .firstName("Test")
                                                            .lastName("Test")
                                                            .phone(PhoneRequest.builder().areaCode("11").number("987654321").build())
                                                            .build())
                                            .shipments(
                                                    PaymentShipmentsRequest.builder()
                                                            .receiverAddress(
                                                                    PaymentReceiverAddressRequest.builder()
                                                                            .zipCode("12312-123")
                                                                            .stateName("Rio de Janeiro")
                                                                            .cityName("Buzios")
                                                                            .streetName("Av das Nacoes Unidas")
                                                                            .streetNumber("3003")
                                                                            .build())
                                                            .build())
                                            .build())
                            .description("Payment for product")
                            .externalReference("MP0001")
                            .installments(1)
                            .order(PaymentOrderRequest.builder().id(1L).type("mercadopago").build())
                            .payer(PaymentPayerRequest.builder().entityType("individual").type("customer").build())
                            .paymentMethodId("mastercard")
                            .transactionAmount(new BigDecimal("58.8"))
                            .build();

            return client.create(createRequest, requestOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Payment createTest2() throws MPException, MPApiException {
        try {
            Map<String, String> customHeaders = new HashMap<>();
            customHeaders.put("x-idempotency-key", UUID.randomUUID().toString());

            MPRequestOptions requestOptions = MPRequestOptions.builder()
                    .customHeaders(customHeaders)
                    .build();

            MercadoPagoConfig.setAccessToken(mpConfig.getAccessToken());

            CustomerCardClient customerCardClient = new CustomerCardClient();
            MPResourceList<CustomerCard> customerCardMPResourceList = customerCardClient.listAll("70202035");

            CardTokenRequest cardTokenRequest = CardTokenRequest.builder()
                    .build();

            PaymentClient client = new PaymentClient();

            // Construa a lista de itens
            List<PaymentItemRequest> items = Arrays.asList(
                    PaymentItemRequest.builder()
                            .id("MLB2907679857")
                            .title("Point Mini")
                            .description("Point product for card payments via Bluetooth.")
                            .pictureUrl("https://http2.mlstatic.com/resources/frontend/statics/growth-sellers-landings/device-mlb-point-i_medium2x.png")
                            .categoryId("electronics")
                            .quantity(1)
                            .unitPrice(new BigDecimal("58.8"))
                            .warranty(false)
                            .build()
            );

            PaymentCreateRequest createRequest = PaymentCreateRequest.builder()
                    .additionalInfo(
                            PaymentAdditionalInfoRequest.builder()
                                    .items(items)
                                    .payer(
                                            PaymentAdditionalInfoPayerRequest.builder()
                                                    .firstName("Test")
                                                    .lastName("Test")
                                                    .phone(
                                                            PhoneRequest.builder()
                                                                    .areaCode("11")
                                                                    .number("987654321")
                                                                    .build()
                                                    )
                                                    .build()
                                    )
                                    .shipments(
                                            PaymentShipmentsRequest.builder()
                                                    .receiverAddress(
                                                            PaymentReceiverAddressRequest.builder()
                                                                    .zipCode("12312-123")
                                                                    .stateName("Rio de Janeiro")
                                                                    .cityName("Buzios")
                                                                    .streetName("Av das Nacoes Unidas")
                                                                    .streetNumber("3003")
                                                                    .build()
                                                    )
                                                    .build()
                                    )
                                    .build()
                    )
                    .description("Payment for product")
                    .externalReference("MP0001")
                    .installments(1)
                    .payer(
                            PaymentPayerRequest.builder()
                                    .entityType("individual")
                                    .type("customer")
                                    .email("test_user_123@testuser.com")
                                    .identification(IdentificationRequest.builder().number("95749019047").build())
                                    .build()
                    )
                    .paymentMethodId("master")
                    .token("ff8080814c11e237014c1ff593b57b4d")
                    .transactionAmount(new BigDecimal("58.8"))
                    .build();

            return client.create(createRequest, requestOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public MPResourceList<PaymentMethod> findAll() throws MPException, MPApiException {
        MercadoPagoConfig.setAccessToken(mpConfig.getAccessToken());

        PaymentMethodClient client = new PaymentMethodClient();
        return client.list();
    }
}