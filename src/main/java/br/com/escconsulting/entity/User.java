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
 * Classe que representa um usuário.
 * <p>
 * Esta classe mapeia a tabela "user" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true, of = "userId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"user\"")
public class User extends AbstractEntity implements Serializable {

	/**
	 * Serial version UID para serialização.
	 */
	@Serial
	private static final long serialVersionUID = -7136861032484571472L;

	/**
	 * Identificador único do usuário.
	 */
	@Id
	@GeneratedValue(generator = "UUID")
	@Column(name = "user_id", updatable = false, nullable = false)
	private UUID userId;

	/**
	 * Identificador do usuário no provedor.
	 */
	@Column(name = "provider_user_id", length = 100)
	private String providerUserId;

	/**
	 * Email do usuário.
	 */
	@Column(name = "email", length = 200)
	private String email;

	/**
	 * Nome de exibição do usuário.
	 */
	@Column(name = "display_name", length = 200, nullable = false)
	private String displayName;

	/**
	 * Senha do usuário.
	 */
	@Column(name = "password", length = 200, nullable = false)
	private String password;

	/**
	 * Código de verificação para recuperação de senha.
	 */
	@Column(name = "forgot_password_verification_code", length = 6)
	private String forgotPasswordVerificationCode;

	/**
	 * Indica se a recuperação de senha foi validada.
	 */
	@Column(name = "forgot_password_validated", nullable = false)
	private Boolean forgotPasswordValidated = false;

	/**
	 * Provedor do usuário.
	 */
	@Column(name = "provider", length = 50, nullable = false)
	private String provider;

	/**
	 * Identificador do arquivo de foto associado ao usuário.
	 */
	@Column(name = "photo_file_id", insertable = false, updatable = false)
	private UUID photoFileId;

	/**
	 * Indica se a foto foi validada.
	 */
	@Column(name = "photo_validated", nullable = false)
	private Boolean photoValidated = false;

	/**
	 * URL da imagem do usuário.
	 */
	@Column(name = "image_url", columnDefinition = "TEXT")
	private String imageURL;

	/**
	 * Arquivo associado à foto do usuário.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "photo_file_id")
	private File file;

	/**
	 * Roles associadas ao usuário.
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "user_role",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles;

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
		if (this.photoValidated == null) {
			this.photoValidated = false;
		}
		if (this.forgotPasswordValidated == null) {
			this.forgotPasswordValidated = false;
		}
	}
}