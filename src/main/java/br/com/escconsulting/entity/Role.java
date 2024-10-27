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
import java.util.Set;
import java.util.UUID;

/**
 * Classe que representa uma função (role).
 * <p>
 * Esta classe mapeia a tabela "role" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true, of = "roleId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role extends AbstractEntity implements Serializable {

	/**
	 * Serial version UID para serialização.
	 */
	@Serial
	private static final long serialVersionUID = -3583817374146038210L;

	/**
	 * Identificador único da role.
	 */
	@Id
	@GeneratedValue(generator = "UUID")
	@Column(name = "role_id", updatable = false, nullable = false)
	private UUID roleId;

	/**
	 * Nome da role.
	 */
	@Column(name = "role_name", length = 100, nullable = false, unique = true)
	private String roleName;

	/**
	 * Usuários associados a esta role.
	 */
	@ManyToMany(mappedBy = "roles")
	private Set<User> users;

	/**
	 * Menus associados a esta role.
	 */
	@OneToMany(mappedBy = "role")
	private Set<RoleMenu> roleMenus;

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