package br.com.escconsulting.dto.customer.bank.account;

import br.com.escconsulting.dto.bank.BankDTO;
import br.com.escconsulting.dto.customer.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerBankAccountDTO {

    private UUID customerBankAccountId;
    private CustomerDTO customer;
    private BankDTO bank;
    private String accountNumber;
    private String accountType;
    private String branchNumber;
    private String pixKey;
}