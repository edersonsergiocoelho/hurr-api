package br.com.escconsulting.entity;

import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/**
 * Classe que representa uma taxa.
 * <p>
 * Esta classe mapeia a tabela "fee" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true, of = "feeId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fee")
public class Fee extends AbstractEntity implements Serializable {

    /**
     * Serial version UID para serialização.
     */
    @Serial
    private static final long serialVersionUID = -7154041710038789812L;

    /**
     * Identificador único da taxa.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "fee_id", updatable = false, nullable = false)
    private UUID feeId;

    /**
     * Tipo de taxa (ex: 'commission', 'service fee').
     */
    @Column(name = "fee_type", length = 50, nullable = false)
    private String feeType;

    /**
     * Valor da taxa.
     */
    @Column(name = "amount", precision = 13, scale = 2, nullable = false)
    private BigDecimal amount;

    /**
     * Data de início de vigência da taxa.
     */
    @Column(name = "start_date", nullable = false)
    private Instant startDate;

    /**
     * Data de término de vigência da taxa.
     */
    @Column(name = "end_date", nullable = false)
    private Instant endDate;

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