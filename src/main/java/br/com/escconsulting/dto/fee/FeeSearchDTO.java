package br.com.escconsulting.dto.fee;

import lombok.Getter;

@Getter
public class FeeSearchDTO {

    private String globalFilter;
    private String feeType;
    private Boolean enabled;
}