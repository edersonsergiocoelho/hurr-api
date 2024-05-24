package br.com.escconsulting.dto.customer;

import br.com.escconsulting.entity.enumeration.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private UUID customerId;
    private String firstName;
    private String lastName;
    private CustomerType customerType;
    private String email;
    private boolean emailValidated;
    private String ddiPhone;
    private String phone;
    private boolean phoneValidated;
    private LocalDate dateOfBirth;
    private String cpf;
    private String identityNumber;
    private String identityNumberIssuingBody;
    private String identityNumberIssuingBodyUF;
    private boolean identityNumberValidated;
    private String driverLicenseRegistrationNumber;
    private String driverLicenseCategory;
    private LocalDate driverLicenseFirstLicenseDate;
    private LocalDate driverLicenseExpirationDate;
    private LocalDate driverLicenseIssueDate;
    private String driverLicenseIssueUF;
    private boolean driverLicenseValidated;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}