package br.com.escconsulting.exception;

import br.com.escconsulting.dto.OAuth2AccessTokenErrorResponse;

public class AccessTokenRequiredException extends RuntimeException {

    private OAuth2AccessTokenErrorResponse error;

    public AccessTokenRequiredException(OAuth2AccessTokenErrorResponse error) {
        super(error.toString());
        this.error = error;
    }

    public OAuth2AccessTokenErrorResponse getError() {
        return this.error;
    }
}