package br.com.escconsulting.entity;

import br.com.escconsulting.entity.enumeration.CustomerType;
import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Classe que representa um cliente.
 * <p>
 * Esta classe mapeia a tabela "customer" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true, of = "customerId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer extends AbstractEntity implements Serializable {

    /**
     * Serial version UID para serialização.
     */
    @Serial
    private static final long serialVersionUID = -10118876876617995L;

    /**
     * Identificador único do cliente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id", updatable = false, nullable = false)
    private UUID customerId;

    /**
     * Primeiro nome do cliente.
     */
    @Column(name = "first_name", length = 100)
    private String firstName;

    /**
     * Último nome do cliente.
     */
    @Column(name = "last_name", length = 100)
    private String lastName;

    /**
     * Tipo de cliente.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "customer_type", nullable = false)
    private CustomerType customerType;

    /**
     * Email do cliente.
     */
    @Column(name = "email", length = 300, unique = true, nullable = false)
    private String email;

    /**
     * Indica se o email foi validado.
     */
    @Column(name = "email_validated", nullable = false)
    private boolean emailValidated;

    /**
     * Código de verificação do email.
     */
    @Column(name = "email_verification_code", nullable = false)
    private String emailVerificationCode;

    /**
     * DDI do telefone do cliente.
     */
    @Column(name = "ddi_phone", length = 10)
    private String ddiPhone;

    /**
     * Telefone do cliente.
     */
    @Column(name = "phone", length = 20)
    private String phone;

    /**
     * Indica se o telefone foi validado.
     */
    @Column(name = "phone_validated", nullable = false)
    private boolean phoneValidated;

    /**
     * Código de verificação do telefone.
     */
    @Column(name = "phone_verification_code", nullable = false)
    private String phoneVerificationCode;

    /**
     * Data de nascimento do cliente.
     */
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    /**
     * CPF do cliente.
     */
    @Column(name = "cpf", length = 20)
    private String cpf;

    /**
     * Número da identidade do cliente.
     */
    @Column(name = "identity_number", length = 20)
    private String identityNumber;

    /**
     * Órgão emissor da identidade do cliente.
     */
    @Column(name = "identity_number_issuing_body", length = 20)
    private String identityNumberIssuingBody;

    /**
     * Unidade federativa do órgão emissor da identidade do cliente.
     */
    @Column(name = "identity_number_issuing_body_uf", length = 10)
    private String identityNumberIssuingBodyUF;

    /**
     * Indica se o número da identidade foi validado.
     */
    @Column(name = "identity_number_validated", nullable = false)
    private boolean identityNumberValidated;

    /**
     * Identificador do arquivo associado ao número da identidade.
     */
    @Column(name = "identity_number_file_id")
    private UUID identityNumberFileId;

    /**
     * Número de registro da carteira de motorista do cliente.
     */
    @Column(name = "driver_license_registration_number", length = 20)
    private String driverLicenseRegistrationNumber;

    /**
     * Categoria da carteira de motorista do cliente.
     */
    @Column(name = "driver_license_category", length = 10)
    private String driverLicenseCategory;

    /**
     * Data da primeira habilitação do cliente.
     */
    @Column(name = "driver_license_first_license_date")
    private LocalDate driverLicenseFirstLicenseDate;

    /**
     * Data de expiração da carteira de motorista do cliente.
     */
    @Column(name = "driver_license_expiration_date")
    private LocalDate driverLicenseExpirationDate;

    /**
     * Data de emissão da carteira de motorista do cliente.
     */
    @Column(name = "driver_license_issue_date")
    private LocalDate driverLicenseIssueDate;

    /**
     * Unidade federativa de emissão da carteira de motorista do cliente.
     */
    @Column(name = "driver_license_issue_uf", length = 10)
    private String driverLicenseIssueUF;

    /**
     * Indica se a carteira de motorista foi validada.
     */
    @Column(name = "driver_license_validated", nullable = false)
    private boolean driverLicenseValidated;

    /**
     * Identificador do arquivo associado à carteira de motorista.
     */
    @Column(name = "driver_license_file_id")
    private UUID driverLicenseFileId;

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