package br.com.escconsulting.entity;

import br.com.escconsulting.entity.enumeration.CustomerType;
import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Table(name = "customer")
public class Customer extends AbstractEntity implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -10118876876617995L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id", updatable = false, nullable = false)
    private UUID customerId;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "customer_type", nullable = false)
    private CustomerType customerType;

    @Column(name = "email", length = 300, unique = true, nullable = false)
    private String email;

    @Column(name = "email_validated", nullable = false)
    private boolean emailValidated;

    @Column(name = "email_verification_code", nullable = false)
    private String emailVerificationCode;

    @Column(name = "ddi_phone", length = 10)
    private String ddiPhone;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "phone_validated", nullable = false)
    private boolean phoneValidated;

    @Column(name = "phone_verification_code", nullable = false)
    private String phoneVerificationCode;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "cpf", length = 20)
    private String cpf;

    @Column(name = "identity_number", length = 20)
    private String identityNumber;

    @Column(name = "identity_number_issuing_body", length = 20)
    private String identityNumberIssuingBody;

    @Column(name = "identity_number_issuing_body_uf", length = 10)
    private String identityNumberIssuingBodyUF;

    @Column(name = "identity_number_validated", nullable = false)
    private boolean identityNumberValidated;

    @Column(name = "identity_number_file_id")
    private UUID identityNumberFileId;

    @Column(name = "driver_license_registration_number", length = 20)
    private String driverLicenseRegistrationNumber;

    @Column(name = "driver_license_category", length = 10)
    private String driverLicenseCategory;

    @Column(name = "driver_license_first_license_date")
    private LocalDate driverLicenseFirstLicenseDate;

    @Column(name = "driver_license_expiration_date")
    private LocalDate driverLicenseExpirationDate;

    @Column(name = "driver_license_issue_date")
    private LocalDate driverLicenseIssueDate;

    @Column(name = "driver_license_issue_uf", length = 10)
    private String driverLicenseIssueUF;

    @Column(name = "driver_license_validated", nullable = false)
    private boolean driverLicenseValidated;

    @Column(name = "driver_license_file_id")
    private UUID driverLicenseFileId;
}