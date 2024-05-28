package br.com.escconsulting.dto.customer.vehicle.booking;

import br.com.escconsulting.dto.customer.CustomerDTO;
import br.com.escconsulting.dto.customer.address.CustomerAddressDTO;
import br.com.escconsulting.dto.customer.vehicle.CustomerVehicleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerVehicleBookingDTO {

    private UUID customerVehicleBookingId;
    private CustomerVehicleDTO customerVehicle;
    private CustomerDTO customer;
    private CustomerAddressDTO customerAddressDelivery;
    private BigDecimal customerAddressDeliveryValue;
    private CustomerAddressDTO customerAddressPickUp;
    private BigDecimal customerAddressPickUpValue;
    private String booking;
    private LocalDate bookingStartDate;
    private LocalDate bookingEndDate;
    private String bookingStartTime;
    private String bookingEndTime;
    private Long bookingStartKM;
    private Long bookingEndKM;
    private LocalDateTime bookingDeliveryDate;
    private BigDecimal totalBookingValue;
    private Long mpPaymentId;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}