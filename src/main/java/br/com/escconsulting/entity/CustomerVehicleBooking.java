package br.com.escconsulting.entity;

import br.com.escconsulting.dto.mercado.pago.MPPaymentDTO;
import br.com.escconsulting.entity.enumeration.BookingStatus;
import br.com.escconsulting.entity.generic.AbstractEntity;
import br.com.escconsulting.repository.converter.MPPaymentDTOConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Classe que representa a reserva de um veículo do cliente.
 * <p>
 * Esta classe mapeia a tabela "customer_vehicle_booking" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true, of = "customerVehicleBookingId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CustomerVehicleBooking extends AbstractEntity implements Serializable {

    /**
     * Serial version UID para serialização.
     */
    @Serial
    private static final long serialVersionUID = -4187382216307699223L;

    /**
     * Identificador único da reserva do veículo do cliente.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "customer_vehicle_booking_id", updatable = false, nullable = false)
    private UUID customerVehicleBookingId;

    /**
     * Veículo associado a esta reserva.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_vehicle_id", nullable = false)
    private CustomerVehicle customerVehicle;

    /**
     * Cliente que fez a reserva.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /**
     * Endereço de cobrança do cliente.
     */
    @ManyToOne
    @JoinColumn(name = "customer_address_billing_id", nullable = false)
    private CustomerAddress customerAddressBilling;

    /**
     * Endereço de entrega do cliente (opcional).
     */
    @ManyToOne
    @JoinColumn(name = "customer_address_delivery_id")
    private CustomerAddress customerAddressDelivery;

    /**
     * Custo associado ao endereço de entrega.
     */
    @Column(name = "customer_address_delivery_value", precision = 13, scale = 2)
    private BigDecimal customerAddressDeliveryValue;

    /**
     * Endereço de retirada do cliente (opcional).
     */
    @ManyToOne
    @JoinColumn(name = "customer_address_pickup_id")
    private CustomerAddress customerAddressPickUp;

    /**
     * Custo associado ao endereço de retirada.
     */
    @Column(name = "customer_address_pickup_value", precision = 13, scale = 2)
    private BigDecimal customerAddressPickUpValue;

    /**
     * Referência única da reserva.
     */
    @Column(name = "booking", length = 100, nullable = false, unique = true)
    private String booking;

    /**
     * Data de início da reserva.
     */
    @Column(name = "reservation_start_date", nullable = false)
    private LocalDate reservationStartDate;

    /**
     * Hora de início da reserva.
     */
    @Column(name = "reservation_start_time", length = 5, nullable = false)
    private String reservationStartTime;

    /**
     * Data de término da reserva.
     */
    @Column(name = "reservation_end_date", nullable = false)
    private LocalDate reservationEndDate;

    /**
     * Hora de término da reserva.
     */
    @Column(name = "reservation_end_time", length = 5, nullable = false)
    private String reservationEndTime;

    /**
     * Número total de dias da reserva.
     */
    @Column(name = "days_reservation", nullable = false)
    private Integer daysReservation;

    /**
     * Tarifa diária da reserva.
     */
    @Column(name = "daily_rate", nullable = false, precision = 13, scale = 2)
    private BigDecimal dailyRate;

    /**
     * Leitura do quilômetro no início da reserva.
     */
    @Column(name = "booking_start_km")
    private Long bookingStartKM;

    /**
     * Leitura do quilômetro no término da reserva.
     */
    @Column(name = "booking_end_km")
    private Long bookingEndKM;

    /**
     * Data e hora de início da reserva.
     */
    @Column(name = "booking_start_date")
    private LocalDateTime bookingStartDate;

    /**
     * Notas de check-in para a reserva.
     */
    @Column(name = "check_in_notes")
    private String checkInNotes;

    /**
     * Data e hora de término da reserva.
     */
    @Column(name = "booking_end_date")
    private LocalDateTime bookingEndDate;

    /**
     * Notas de check-out para a reserva.
     */
    @Column(name = "check_out_notes")
    private String checkOutNotes;

    /**
     * Data e hora em que a reserva foi cancelada.
     */
    @Column(name = "booking_cancellation_date")
    private LocalDateTime bookingCancellationDate;

    /**
     * Valor disponível para retirada após dedução da taxa do site.
     */
    @Column(name = "withdrawable_booking_value", nullable = false, precision = 13, scale = 2)
    private BigDecimal withdrawableBookingValue;

    /**
     * Valor total da reserva.
     */
    @Column(name = "total_booking_value", nullable = false, precision = 13, scale = 2)
    private BigDecimal totalBookingValue;

    /**
     * Valor total de adicionais da reserva.
     */
    @Column(name = "total_additional_value", precision = 13, scale = 2)
    private BigDecimal totalAdditionalValue;

    /**
     * Valor total final da reserva.
     */
    @Column(name = "total_final_booking_value", nullable = false, precision = 13, scale = 2)
    private BigDecimal totalFinalBookingValue;

    /**
     * Status da reserva.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status", length = 50, nullable = false)
    private BookingStatus bookingStatus;

    /**
     * O identificador do pagamento do gateway de pagamento.
     */
    @Column(name = "mp_payment_id", nullable = false)
    private Long mpPaymentId;

    /**
     * Dados de pagamento do gateway de pagamento em formato JSONB.
     */
    @ColumnTransformer(write = "?::jsonb")
    @Column(name = "mp_payment", columnDefinition = "jsonb")
    @Convert(converter = MPPaymentDTOConverter.class)
    private MPPaymentDTO mpPayment;

    /**
     * Dados de pagamento do gateway de pagamento em formato JSONB.
     */
    @ColumnTransformer(write = "?::jsonb")
    @Column(name = "mp_payment_refund", columnDefinition = "jsonb")
    @Convert(converter = MPPaymentDTOConverter.class)
    private MPPaymentDTO mpPaymentRefund;

    /**
     * O identificador do pagamento do gateway de pagamento.
     */
    @Column(name = "mp_payment_additional_id", nullable = false)
    private Long mpPaymentAdditionalId;

    /**
     * Dados de pagamento do gateway de pagamento em formato JSONB.
     */
    @ColumnTransformer(write = "?::jsonb")
    @Column(name = "mp_payment_additional", columnDefinition = "jsonb")
    @Convert(converter = MPPaymentDTOConverter.class)
    private MPPaymentDTO mpPaymentAdditional;

    /**
     * Método de callback executado antes da persistência da entidade.
     */
    @PrePersist
    protected void prePersist() {
        // Verifica se a data de criação está nula; se estiver, define a data atual.
        if (this.getCreatedDate() == null) {
            this.setCreatedDate(Instant.now());
        }

        // Verifica se o status da reserva está nulo; se estiver, define como "RESERVED".
        if (this.getBookingStatus() == null) {
            this.setBookingStatus(BookingStatus.RESERVED);
        }

        // Verifica se o campo "enabled" está nulo; se estiver, define como verdadeiro.
        if (this.getEnabled() == null) {
            this.setEnabled(Boolean.TRUE);
        }
    }
}