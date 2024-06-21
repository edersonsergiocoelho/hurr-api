package br.com.escconsulting.dto.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankDTO {

    private UUID bankId;
    private String bankCode;
    private String bankName;
}