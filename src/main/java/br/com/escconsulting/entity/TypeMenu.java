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
 * Classe que representa um tipo de menu.
 * <p>
 * Esta classe mapeia a tabela "type_menu" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true, of = "typeMenuId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "type_menu")
public class TypeMenu extends AbstractEntity implements Serializable {

    /**
     * Serial version UID para serialização.
     */
    @Serial
    private static final long serialVersionUID = 3110939456718046104L;

    /**
     * Identificador único do tipo de menu.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "type_menu_id", updatable = false, nullable = false)
    private UUID typeMenuId;

    /**
     * Nome do tipo de menu.
     */
    @Column(name = "type_menu_name", nullable = false, unique = true, length = 100)
    private String typeMenuName;

    /**
     * Descrição do tipo de menu.
     */
    @Column(name = "description", nullable = false)
    private String description;

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