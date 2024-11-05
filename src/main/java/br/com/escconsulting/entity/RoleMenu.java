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

/**
 * Classe que representa a associação entre uma função (role) e um menu.
 * <p>
 * Esta classe mapeia a tabela "role_menu" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true, of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role_menu")
public class RoleMenu extends AbstractEntity implements Serializable {

    /**
     * Serial version UID para serialização.
     */
    @Serial
    private static final long serialVersionUID = 3110939456718046104L;

    /**
     * Identificador composto da associação entre uma função (role) e um menu.
     */
    @EmbeddedId
    private RoleMenuId id;

    /**
     * Função (role) associada.
     */
    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Role role;

    /**
     * Menu associado.
     */
    @ManyToOne
    @MapsId("menuId")
    @JoinColumn(name = "menu_id")
    private Menu menu;

    /**
     * Tipo de menu associado.
     */
    @ManyToOne
    @JoinColumn(name = "type_menu_id", nullable = false)
    private TypeMenu typeMenu;

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