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
 * Classe que representa uma cidade.
 * <p>
 * Esta classe mapeia a tabela "city" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true, of = "cityId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "city")
public class City extends AbstractEntity implements Serializable {

    /**
     * Serial version UID para serialização.
     */
    @Serial
    private static final long serialVersionUID = -3373295098868142075L;

    /**
     * Identificador único da cidade.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "city_id", updatable = false, nullable = false)
    private UUID cityId;

    /**
     * Nome da cidade.
     */
    @Column(name = "city_name", length = 100, nullable = false, unique = true)
    private String cityName;

    /**
     * Estado ao qual a cidade pertence.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    /**
     * Indica se o serviço está disponível na cidade.
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