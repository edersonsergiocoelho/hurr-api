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
 * Classe que representa um país.
 * <p>
 * Esta classe mapeia a tabela "country" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true, of = "countryId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "country")
public class Country extends AbstractEntity implements Serializable {

    /**
     * Serial version UID para serialização.
     */
    @Serial
    private static final long serialVersionUID = 3110939456718046104L;

    /**
     * Identificador único do país.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "country_id", updatable = false, nullable = false)
    private UUID countryId;

    /**
     * Nome do país.
     */
    @Column(name = "country_name", length = 100, nullable = false, unique = true)
    private String countryName;

    /**
     * Código do país.
     */
    @Column(name = "country_code", length = 5, nullable = false, unique = true)
    private String countryCode;

    /**
     * Indica se o serviço está disponível no país.
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