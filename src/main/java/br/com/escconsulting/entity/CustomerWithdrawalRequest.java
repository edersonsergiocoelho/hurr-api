package br.com.escconsulting.entity;

import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_withdrawal_request")
public class CustomerWithdrawalRequest extends AbstractEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Identificador único do pedido de retirada.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "customer_withdrawal_request_id", updatable = false, nullable = false)
    private UUID customerWithdrawalRequestId;

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
    @JoinColumn(name = "customer_bank_account", nullable = false)
    private CustomerBankAccount customerBankAccount;

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
    @Column(name = "withdrawal_date", nullable = false)
    private LocalDateTime withdrawalDate;
}