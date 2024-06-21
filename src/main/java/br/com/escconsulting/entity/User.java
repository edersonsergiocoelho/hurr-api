package br.com.escconsulting.entity;

import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
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
	@GeneratedValue(generator = "UUID")
	@Column(name = "user_id", updatable = false, nullable = false)
	private UUID userId;

	@Column(name = "provider_user_id", length = 100)
	private String providerUserId;

	@Column(name = "email", length = 200)
	private String email;

	@Column(name = "display_name", length = 200, nullable = false)
	private String displayName;

	@Column(name = "password", length = 200, nullable = false)
	private String password;

	@Column(name = "forgot_password_verification_code", length = 6)
	private String forgotPasswordVerificationCode;

	@Column(name = "forgot_password_validated", nullable = false)
	private Boolean forgotPasswordValidated = false;

	@Column(name = "provider", length = 50, nullable = false)
	private String provider;

	@Column(name = "photo_file_id")
	private UUID photoFileId;

	@Column(name = "photo_validated", nullable = false)
	private Boolean photoValidated = false;

	@Column(name = "image_url", columnDefinition = "TEXT")
	private String imageURL;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@PrePersist
	protected void prePersist() {
		if (this.photoValidated == null) {
			this.photoValidated = false;
		}

		if (this.forgotPasswordValidated == null) {
			this.forgotPasswordValidated = false;
		}
	}
}