package br.com.escconsulting.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

/**
 * Classe que representa o ID composto da associação entre um usuário e uma função (role).
 * <p>
 * Esta classe mapeia a chave primária composta da tabela "user_role" no banco de dados.
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
public class UserRoleId implements Serializable {

    /**
     * Identificador do usuário.
     */
    @Column(name = "user_id")
    private UUID userId;

    /**
     * Identificador da role.
     */
    @Column(name = "role_id")
    private UUID roleId;
}