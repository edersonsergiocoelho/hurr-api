package br.com.escconsulting.dto.customer.vehicle.bank.account;

import br.com.escconsulting.dto.bank.BankDTO;
import br.com.escconsulting.dto.customer.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerVehicleBankAccountDTO {

    private UUID customerVehicleBankAccountId;
    private CustomerDTO customer;
    private BankDTO bank;
    private String pixType;
    private String pixKey;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}