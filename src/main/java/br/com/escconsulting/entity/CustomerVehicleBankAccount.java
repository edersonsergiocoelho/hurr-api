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
import java.util.UUID;

/**
 * Classe que representa a conta bancária de um veículo do cliente.
 * <p>
 * Esta classe mapeia a tabela "customer_vehicle_bank_account" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true, of = "customerVehicleBankAccountId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_vehicle_bank_account")
public class CustomerVehicleBankAccount extends AbstractEntity implements Serializable {

    /**
     * Serial version UID para serialização.
     */
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