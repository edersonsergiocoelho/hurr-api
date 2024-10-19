package br.com.escconsulting.dto;

import lombok.Value;

@Value
public class APIResponse {

    private Boolean success;
    private String message;
}