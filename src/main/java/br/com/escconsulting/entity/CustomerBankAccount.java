package br.com.escconsulting.entity;

import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidade que representa uma conta bancária do cliente.
 * Esta entidade mapeia para a tabela "customer_bank_account".
 *
 * @autor Ederson Sergio Monteiro Coelho
 */

@Entity
@Table(name = "customer_bank_account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "customerBankAccountId", callSuper = false)
@ToString
public class CustomerBankAccount extends AbstractEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -5223744745154642982L;

    /**
     * Identificador único da conta bancária do cliente.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "customer_bank_account_id", updatable = false, nullable = false)
    private UUID customerBankAccountId;

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
     * Número da conta bancária.
     */
    @Column(name = "account_number", length = 20, nullable = false)
    private String accountNumber;

    /**
     * Tipo da conta bancária, por exemplo, "corrente" ou "poupança".
     */
    @Column(name = "account_type", length = 20, nullable = false)
    private String accountType;

    /**
     * Número da agência bancária.
     */
    @Column(name = "branch_number", length = 20, nullable = false)
    private String branchNumber;

    /**
     * Chave Pix associada à conta bancária.
     */
    @Column(name = "pix_key", length = 100)
    private String pixKey;
}