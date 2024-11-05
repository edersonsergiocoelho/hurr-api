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
 * Classe que representa um estado.
 * <p>
 * Esta classe mapeia a tabela "state" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true, of = "stateId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "state")
public class State extends AbstractEntity implements Serializable {

    /**
     * Serial version UID para serialização.
     */
    @Serial
    private static final long serialVersionUID = -8919746397847071221L;

    /**
     * Identificador único do estado.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "state_id", updatable = false, nullable = false)
    private UUID stateId;

    /**
     * Nome do estado.
     */
    @Column(name = "state_name", length = 100, nullable = false, unique = true)
    private String stateName;

    /**
     * País ao qual o estado pertence.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    /**
     * Indica se o serviço está disponível no estado.
     */
    @Column(name = "service_available", nullable = false)
    private Boolean serviceAvailable;

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