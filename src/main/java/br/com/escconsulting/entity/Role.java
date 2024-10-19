package br.com.escconsulting.entity;

import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "roleId")
@ToString(of = {"roleId"})
@Table(name = "role")
public class Role extends AbstractEntity implements Serializable {

	/**
	 *
	 */
	@Serial
	private static final long serialVersionUID = -3583817374146038210L;

	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_MODERATOR = "ROLE_MODERATOR";

	@Id
	@GeneratedValue(generator = "UUID")
	@Column(name = "role_id", updatable = false, nullable = false)
	private UUID roleId;

	@Column(name = "role_name", length = 100, nullable = false, unique = true)
	private String roleName;

	@ManyToMany(mappedBy = "roles")
	private Set<User> users;

	@OneToMany(mappedBy = "role")
	private Set<RoleMenu> roleMenus;
}