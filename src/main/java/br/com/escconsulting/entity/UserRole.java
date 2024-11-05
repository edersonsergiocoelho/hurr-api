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
 * Classe que representa a associação entre usuário e função (role).
 * <p>
 * Esta classe mapeia a tabela "user_role" no banco de dados.
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
@Table(name = "user_role")
public class UserRole extends AbstractEntity implements Serializable {

    /**
     * Serial version UID para serialização.
     */
    @Serial
    private static final long serialVersionUID = -5527071434834666042L;

    /**
     * Identificador composto da associação entre usuário e função (role).
     */
    @EmbeddedId
    private UserRoleId id;

    /**
     * Usuário associado.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Função (role) associada.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Role role;

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