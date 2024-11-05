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
 * Classe que representa um método de pagamento.
 * <p>
 * Esta classe mapeia a tabela "payment_method" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true, of = "paymentMethodId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment_method")
public class PaymentMethod extends AbstractEntity implements Serializable {

    /**
     * Serial version UID para serialização.
     */
    @Serial
    private static final long serialVersionUID = -8425767129811040779L;

    /**
     * Identificador único do método de pagamento.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "payment_method_id", updatable = false, nullable = false)
    private UUID paymentMethodId;

    /**
     * Nome do método de pagamento.
     */
    @Column(name = "payment_method_name", length = 100, nullable = false, unique = true)
    private String paymentMethodName;

    /**
     * Identificador do arquivo associado ao método de pagamento.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private File file;

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