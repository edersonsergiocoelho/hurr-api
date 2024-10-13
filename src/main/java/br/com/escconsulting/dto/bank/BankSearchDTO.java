package br.com.escconsulting.dto.bank;

import lombok.Getter;

@Getter
public class BankSearchDTO {

    private String globalFilter;
    private String bankName;
    private Boolean enabled;
}