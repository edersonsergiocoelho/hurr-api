package br.com.escconsulting.entity;

import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Table(name = "customer_vehicle_booking")
public class CustomerVehicleBooking extends AbstractEntity implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -4187382216307699223L;

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "customer_vehicle_booking_id", updatable = false, nullable = false)
    private UUID customerVehicleBookingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_vehicle_id", nullable = false)
    private CustomerVehicle customerVehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "booking", length = 100, nullable = false, unique = true)
    private String booking;

    @Column(name = "booking_start_date", nullable = false)
    private LocalDate bookingStartDate;

    @Column(name = "booking_end_date", nullable = false)
    private LocalDate bookingEndDate;

    @Column(name = "booking_start_time", length = 5, nullable = false)
    private String bookingStartTime;

    @Column(name = "booking_end_time", length = 5, nullable = false)
    private String bookingEndTime;

    @Column(name = "booking_delivery_date")
    private LocalDateTime bookingDeliveryDate;

    @Column(name = "total_booking_value", precision = 13, scale = 2, nullable = false)
    private BigDecimal totalBookingValue;

    @Column(name = "mp_payment_id", nullable = false)
    private Long mpPaymentId;
}