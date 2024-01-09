package br.com.escconsulting.entity;

import br.com.escconsulting.entity.generic.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
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
@EqualsAndHashCode(callSuper = false, of = "id")
@ToString
@Table(name = "role")
public class Role extends AbstractEntity implements Serializable {

	/**
	 *
	 */
	@Serial
	private static final long serialVersionUID = -3583817374146038210L;

	public static final String USER = "USER";
	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_MODERATOR = "ROLE_MODERATOR";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private UUID id;

	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "role")
	private Set<UserRole> userRoles = new HashSet<>();
}