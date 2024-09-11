package br.com.escconsulting.dto;

import lombok.Value;

@Value
public class APIResponseDTO {

    private Boolean success;
    private String message;
}