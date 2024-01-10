package br.com.escconsulting.entity;

import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "id")
@ToString
@Table(name = "\"user\"")
public class User extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -7136861032484571472L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private UUID id;

	@Column(name = "provider_user_id")
	private String providerUserId;

	private String email;

	@Column(name = "display_name")
	private String displayName;

	private String password;

	private String provider;

	@Column(name = "image_url")
	private String imageURL;

	//@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@Transient
	private Set<UserRole> userRoles = new HashSet<>();

	@Transient
	private Set<Role> roles;
}