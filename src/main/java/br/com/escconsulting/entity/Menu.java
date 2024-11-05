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
import java.util.List;
import java.util.UUID;

/**
 * Classe que representa um menu.
 * <p>
 * Esta classe mapeia a tabela "menu" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true, of = "menuId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "menu")
public class Menu extends AbstractEntity implements Serializable {

    /**
     * Serial version UID para serialização.
     */
    @Serial
    private static final long serialVersionUID = 3110939456718046104L;

    /**
     * Identificador único do menu.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "menuId", updatable = false, nullable = false)
    private UUID menuId;

    /**
     * Nome do menu.
     */
    @Column(name = "name", nullable = false, length = 200)
    private String name;

    /**
     * Descrição do menu.
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * Ícone do menu.
     */
    @Column(name = "icon", length = 100)
    private String icon;

    /**
     * Menu pai, se existir.
     */
    @ManyToOne
    @JoinColumn(name = "menu_parent_id")
    private Menu menuParent;

    /**
     * URL do menu.
     */
    @Column(name = "url", length = 200)
    private String url;

    /**
     * Ordem do menu.
     */
    @Column(name = "menu_order")
    private Integer menuOrder;

    /**
     * Submenus associados.
     */
    @OneToMany(mappedBy = "menuParent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Menu> subMenus;

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