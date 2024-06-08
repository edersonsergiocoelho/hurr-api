package br.com.escconsulting.service.mercado.pago.impl;

import br.com.escconsulting.config.MPConfig;
import br.com.escconsulting.entity.CustomerVehicleBooking;
import br.com.escconsulting.service.CustomerAddressService;
import br.com.escconsulting.service.CustomerService;
import br.com.escconsulting.service.CustomerVehicleBookingService;
import br.com.escconsulting.service.impl.CustomerVehicleServiceImpl;
import br.com.escconsulting.service.mercado.pago.MPWebhookService;
import br.com.escconsulting.util.RandomCodeGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MPWebhookServiceImpl implements MPWebhookService {

    private final MPConfig mpConfig;

    private final CustomerService customerService;

    private final CustomerAddressService customerAddressService;

    private final CustomerVehicleServiceImpl customerVehicleServiceImpl;

    private final CustomerVehicleBookingService customerVehicleBookingService;

    @Override
    public ResponseEntity<String> webhook(String notification) throws JsonProcessingException, MPException, MPApiException {

        Map<String, Object> notificationObjectMapper = new ObjectMapper().readValue(notification, Map.class);

        String action = (String) notificationObjectMapper.get("action");

        if (action != null) {
            System.out.println("MercadoPago Webhook - Received Notification: " + notification);

            String paymentId = (String) ((Map<String, Object>) notificationObjectMapper.get("data")).get("id");

            MercadoPagoConfig.setAccessToken(mpConfig.getAccessToken());

            PaymentClient paymentClient = new PaymentClient();

            Payment payment = paymentClient.get(Long.parseLong(paymentId));

            String customerId = (String) payment.getMetadata().get("customer_id");
            String customerVehicleId = (String) payment.getMetadata().get("customer_vehicle_id");

            String customerAddressDeliveryId = (String) payment.getMetadata().get("customer_address_delivery_id");

            Double customerAddressDeliveryValueDouble = (Double) payment.getMetadata().get("customer_address_delivery_value");
            BigDecimal customerAddressDeliveryValue = null;
            if (customerAddressDeliveryValueDouble != null) {
                customerAddressDeliveryValue= BigDecimal.valueOf(customerAddressDeliveryValueDouble);
            }

            String customerAddressPickUpId = (String) payment.getMetadata().get("customer_address_pickup_id");

            Double customerAddressPickUpValueDouble = (Double) payment.getMetadata().get("customer_address_pickup_value");
            BigDecimal customerAddressPickUpValue = null;
            if (customerAddressPickUpValueDouble != null) {
                customerAddressPickUpValue = BigDecimal.valueOf(customerAddressPickUpValueDouble);
            }

            String preferenceId = (String) payment.getMetadata().get("preference_id");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

            String bookingStartDateString = (String) payment.getMetadata().get("booking_start_date");
            LocalDateTime reservationStartDate = LocalDateTime.parse(bookingStartDateString, formatter);

            String reservationStartTime = (String) payment.getMetadata().get("booking_start_time");

            String bookingEndDateString = (String) payment.getMetadata().get("booking_end_date");
            LocalDateTime reservationEndDate = LocalDateTime.parse(bookingEndDateString, formatter);

            String reservationEndTime = (String) payment.getMetadata().get("booking_end_time");

            Double totalBookingValueDouble = (Double) payment.getMetadata().get("total_booking_value");
            BigDecimal totalBookingValue = BigDecimal.valueOf(totalBookingValueDouble);

            String booking;
            do {
                booking = RandomCodeGenerator.generateCode(10).toUpperCase();
            } while (customerVehicleBookingService.existsByBooking(booking));

            CustomerVehicleBooking customerVehicleBooking = new CustomerVehicleBooking();

            customerVehicleBooking.setBooking(booking);

            customerVehicleBooking.setCustomer(customerService.findById(UUID.fromString(customerId)).get());
            customerVehicleBooking.setCustomerVehicle(customerVehicleServiceImpl.findById(UUID.fromString(customerVehicleId)).get());

            if (customerAddressDeliveryId != null) {
                customerVehicleBooking.setCustomerAddressDelivery(customerAddressService.findById(UUID.fromString(customerAddressDeliveryId)).get());
                customerVehicleBooking.setCustomerAddressDeliveryValue(customerAddressDeliveryValue);
            }

            if (customerAddressPickUpId != null) {
                customerVehicleBooking.setCustomerAddressPickUp(customerAddressService.findById(UUID.fromString(customerAddressPickUpId)).get());
                customerVehicleBooking.setCustomerAddressPickUpValue(customerAddressPickUpValue);
            }

            customerVehicleBooking.setReservationStartDate(reservationStartDate.toLocalDate());
            customerVehicleBooking.setReservationStartTime(reservationStartTime);
            customerVehicleBooking.setReservationEndDate(reservationEndDate.toLocalDate());
            customerVehicleBooking.setReservationEndTime(reservationEndTime);
            customerVehicleBooking.setWithdrawableBookingValue(totalBookingValue.subtract(new BigDecimal(15)));
            customerVehicleBooking.setTotalBookingValue(totalBookingValue);
            customerVehicleBooking.setMpPaymentId(payment.getId());

            Optional<CustomerVehicleBooking> optionalCustomerVehicleBookingSaved = customerVehicleBookingService.save(customerVehicleBooking);

            PreferenceClient preferenceClient = new PreferenceClient();
            Preference preference = preferenceClient.get(preferenceId);

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .metadata(preference.getMetadata())
                    .build();

            preferenceRequest.getMetadata().put("customerVehicleBookingId", optionalCustomerVehicleBookingSaved.get().getCustomerVehicleBookingId());

            preferenceClient.update(preference.getId(), preferenceRequest);

            System.out.println("MercadoPago Webhook - Payment Detail: " + payment.toString());
        }

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}