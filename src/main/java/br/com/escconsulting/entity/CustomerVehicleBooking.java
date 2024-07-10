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

/**
 * Entidade que representa uma reserva de veículo do cliente.
 * Esta entidade mapeia para a tabela "customer_vehicle_booking".
 *
 * @autor Ederson Sergio Monteiro Coelho
 */

@Entity
@Table(name = "customer_vehicle_booking")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "customerVehicleBookingId", callSuper = false)
@ToString
public class CustomerVehicleBooking extends AbstractEntity implements Serializable {

    /**
     * Identificador único para serialização.
     */
    @Serial
    private static final long serialVersionUID = -4187382216307699223L;

    /**
     * Identificador único para a reserva de veículo do cliente.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "customer_vehicle_booking_id", updatable = false, nullable = false)
    private UUID customerVehicleBookingId;

    /**
     * O veículo do cliente associado a esta reserva.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_vehicle_id", nullable = false)
    private CustomerVehicle customerVehicle;

    /**
     * O cliente que fez a reserva.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /**
     * O endereço de entrega do cliente, se aplicável.
     */
    @ManyToOne
    @JoinColumn(name = "customer_address_delivery_id")
    private CustomerAddress customerAddressDelivery;

    /**
     * O custo associado ao endereço de entrega.
     */
    @Column(name = "customer_address_delivery_value", precision = 13, scale = 2)
    private BigDecimal customerAddressDeliveryValue;

    /**
     * O endereço de retirada do cliente, se aplicável.
     */
    @ManyToOne
    @JoinColumn(name = "customer_address_pickup_id")
    private CustomerAddress customerAddressPickUp;

    /**
     * O custo associado ao endereço de retirada.
     */
    @Column(name = "customer_address_pickup_value", precision = 13, scale = 2)
    private BigDecimal customerAddressPickUpValue;

    /**
     * Referência única da reserva.
     */
    @Column(name = "booking", length = 100, nullable = false, unique = true)
    private String booking;

    /**
     * A data de início da reserva.
     */
    @Column(name = "reservation_start_date", nullable = false)
    private LocalDate reservationStartDate;

    /**
     * A hora de início da reserva.
     */
    @Column(name = "reservation_start_time", length = 5, nullable = false)
    private String reservationStartTime;

    /**
     * A data de término da reserva.
     */
    @Column(name = "reservation_end_date", nullable = false)
    private LocalDate reservationEndDate;

    /**
     * A hora de término da reserva.
     */
    @Column(name = "reservation_end_time", length = 5, nullable = false)
    private String reservationEndTime;

    /**
     * A leitura do quilômetro inicial no início da reserva.
     */
    @Column(name = "booking_start_km")
    private Long bookingStartKM;

    /**
     * A leitura do quilômetro final no término da reserva.
     */
    @Column(name = "booking_end_km")
    private Long bookingEndKM;

    /**
     * A data e hora de início da reserva.
     */
    @Column(name = "booking_start_date")
    private LocalDateTime bookingStartDate;

    /**
     * A data e hora de término da reserva.
     */
    @Column(name = "booking_end_date")
    private LocalDateTime bookingEndDate;

    /**
     * A data e hora em que a reserva foi cancelada.
     */
    @Column(name = "booking_cancellation_date")
    private LocalDateTime bookingCancellationDate;

    /**
     * O valor disponível para retirada após a dedução da taxa do site.
     */
    @Column(name = "withdrawable_booking_value", nullable = false, precision = 13, scale = 2)
    private BigDecimal withdrawableBookingValue;

    /**
     * O valor total da reserva.
     */
    @Column(name = "total_booking_value", nullable = false, precision = 13, scale = 2)
    private BigDecimal totalBookingValue;

    /**
     * O identificador do pagamento do gateway de pagamento.
     */
    @Column(name = "mp_payment_id", nullable = false)
    private Long mpPaymentId;

    /**
     * Dados de pagamento do gateway de pagamento em formato JSONB.
     */
    @Column(name = "mp_payment_data", columnDefinition = "jsonb")
    private String mpPaymentData;
}