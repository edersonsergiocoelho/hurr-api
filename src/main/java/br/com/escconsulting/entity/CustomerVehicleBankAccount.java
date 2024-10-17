package br.com.escconsulting.entity;

import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * Entidade que representa uma conta bancária do cliente.
 * Esta entidade mapeia para a tabela "customer_bank_account".
 *
 * @autor Ederson Sergio Monteiro Coelho
 */

@Entity
@Table(name = "customer_vehicle_bank_account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "customerVehicleBankAccountId", callSuper = false)
@ToString
public class CustomerVehicleBankAccount extends AbstractEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -5223744745154642982L;

    /**
     * Identificador único da conta bancária do cliente.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "customer_vehicle_bank_account_id", updatable = false, nullable = false)
    private UUID customerVehicleBankAccountId;

    /**
     * Identificador do cliente.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /**
     * Identificador do banco.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;

    /**
     * Tipo de Pix associada à conta bancária.
     */
    @Column(name = "pix_type", length = 20)
    private String pixType;

    /**
     * Chave Pix associada à conta bancária.
     */
    @Column(name = "pix_key", length = 100)
    private String pixKey;

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