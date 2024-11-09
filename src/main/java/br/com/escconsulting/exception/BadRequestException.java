package br.com.escconsulting.exception;

import lombok.Getter;

/**
 *
 * @author Ederson Sergio Monteiro Coelho
 *
 */
@Getter
public class BadRequestException extends RuntimeException {

	private final String messageKey;

	public BadRequestException(String messageKey) {
		super(messageKey);
		this.messageKey = messageKey;
	}
}