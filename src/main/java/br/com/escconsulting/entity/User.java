package br.com.escconsulting.entity;

import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "userId")
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
	private UUID userId;

	@Column(name = "provider_user_id")
	private String providerUserId;

	private String email;

	@Column(name = "display_name")
	private String displayName;

	private String password;

	@Column(name = "forgot_password_verification_code")
	private String forgotPasswordVerificationCode;

	@Column(name = "forgot_password_validated", nullable = false)
	private Boolean forgotPasswordValidated;

	private String provider;

	@Column(name = "photo_file_id")
	private UUID photoFileId;

	@Column(name = "image_url")
	private String imageURL;

	@Column(name = "photo_validated", nullable = false)
	private Boolean photoValidated;

	@PrePersist
	protected void prePersist() {
		if (this.photoValidated == null) {
			this.photoValidated = false;
		}
	}

	@Transient
	private Set<UserRole> userRoles = new HashSet<>();

	@Transient
	private Set<Role> roles;
}