package br.com.escconsulting.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * Classe que representa o ID composto da associação entre uma função (role) e um menu.
 * <p>
 * Esta classe mapeia a chave primária composta da tabela "role_menu" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class RoleMenuId implements Serializable {

    /**
     * Identificador da role.
     */
    @Column(name = "role_id")
    private UUID roleId;

    /**
     * Identificador do menu.
     */
    @Column(name = "menu_id")
    private UUID menuId;
}