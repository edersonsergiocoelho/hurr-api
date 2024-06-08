package br.com.escconsulting.entity.generic;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

/**
 * Classe abstrata base para entidades que contém os campos comuns de auditoria.
 * Todas as entidades que estendem esta classe herdarão automaticamente os campos de auditoria.
 *
 * @autor Ederson Sergio Monteiro Coelho
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AbstractEntity {

    /**
     * Data e hora de criação do registro.
     */
    @CreatedDate
    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    /**
     * Data e hora da última modificação do registro.
     */
    @LastModifiedDate
    @Column(name = "modified_date")
    private Instant modifiedDate;

    /**
     * Indica se o registro está ativo.
     */
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;
}