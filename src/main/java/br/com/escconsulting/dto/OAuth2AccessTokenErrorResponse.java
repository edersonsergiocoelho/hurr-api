package br.com.escconsulting.dto;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.core.OAuth2Error;

public class OAuth2AccessTokenErrorResponse extends OAuth2Error {

    private HttpStatus httpStatus;

    public OAuth2AccessTokenErrorResponse(HttpStatus httpStatus, String errorCode, String description) {
        super(errorCode, description, null);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}