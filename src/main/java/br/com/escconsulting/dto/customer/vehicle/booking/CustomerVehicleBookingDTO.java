package br.com.escconsulting.dto.customer.vehicle.booking;

import br.com.escconsulting.dto.customer.CustomerDTO;
import br.com.escconsulting.dto.customer.address.CustomerAddressDTO;
import br.com.escconsulting.dto.customer.vehicle.CustomerVehicleDTO;
import br.com.escconsulting.dto.mercado.pago.MPPaymentDTO;
import br.com.escconsulting.entity.enumeration.BookingStatus;
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
    private CustomerAddressDTO customerAddressBilling;
    private CustomerAddressDTO customerAddressDelivery;
    private BigDecimal customerAddressDeliveryValue;
    private CustomerAddressDTO customerAddressPickUp;
    private BigDecimal customerAddressPickUpValue;
    private String booking;
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;
    private String reservationStartTime;
    private String reservationEndTime;
    private Long bookingStartKM;
    private Long bookingEndKM;
    private LocalDateTime bookingStartDate;
    private String checkInNotes;
    private LocalDateTime bookingEndDate;
    private String checkOutNotes;
    private LocalDateTime bookingCancellationDate;
    private BigDecimal withdrawableBookingValue;
    private BigDecimal totalBookingValue;
    private BigDecimal totalAdditionalValue;
    private BigDecimal totalFinalBookingValue;
    private BookingStatus bookingStatus;
    private Long mpPaymentId;
    private MPPaymentDTO mpPayment;
    private MPPaymentDTO mpPaymentRefund;
    private Long mpPaymentAdditionalId;
    private MPPaymentDTO mpPaymentAdditional;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}