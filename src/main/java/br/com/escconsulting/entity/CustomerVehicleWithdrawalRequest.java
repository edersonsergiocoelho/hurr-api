package br.com.escconsulting.entity;

import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Classe que representa um pedido de retirada de um veículo do cliente.
 * <p>
 * Esta classe mapeia a tabela "customer_vehicle_withdrawal_request" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true, of = "customerVehicleWithdrawalRequestId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_vehicle_withdrawal_request")
public class CustomerVehicleWithdrawalRequest extends AbstractEntity implements Serializable {

    /**
     * Serial version UID para serialização.
     */
    @Serial
    private static final long serialVersionUID = 7748061361311179779L;

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

    /**
     * Método de callback executado antes da persistência da entidade.
     */
    @PrePersist
    protected void prePersist() {
        if (this.getCreatedDate() == null) {
            this.setCreatedDate(Instant.now());
        }
        if (this.getEnabled() == null) {
            this.setEnabled(Boolean.TRUE);
        }
    }
}