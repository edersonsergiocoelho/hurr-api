package br.com.escconsulting.entity;

import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_vehicle_withdrawal_request")
public class CustomerVehicleWithdrawalRequest extends AbstractEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Identificador único do pedido de retirada.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "customer_vehicle_withdrawal_request_id", updatable = false, nullable = false)
    private UUID customerVehicleWithdrawalRequestId;

    /**
     * Identificador do cliente.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /**
     * Identificador da reserva do veículo do cliente.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_vehicle_booking_id", nullable = false)
    private CustomerVehicleBooking customerVehicleBooking;

    /**
     * Identificador da conta bancária do cliente.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_vehicle_bank_account", nullable = false)
    private CustomerVehicleBankAccount customerVehicleBankAccount;

    /**
     * Identificador do método de pagamento usado para a retirada.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;

    /**
     * Identificador do status do pagamento.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_status_id", nullable = false)
    private PaymentStatus paymentStatus;

    /**
     * Data e hora da retirada.
     */
    @Column(name = "withdrawal_date")
    private LocalDateTime withdrawalDate;
}