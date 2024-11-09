package br.com.escconsulting.exception;

import br.com.escconsulting.dto.OAuth2AccessTokenErrorResponse;
import lombok.Getter;

/**
 *
 * @author Ederson Sergio Monteiro Coelho
 *
 */
@Getter
public class AccessTokenRequiredException extends RuntimeException {

    private final OAuth2AccessTokenErrorResponse error;

    public AccessTokenRequiredException(OAuth2AccessTokenErrorResponse error) {
        super(error.toString());
        this.error = error;
    }
}