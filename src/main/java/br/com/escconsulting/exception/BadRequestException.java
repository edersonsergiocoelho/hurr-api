package br.com.escconsulting.exception;

public class BadRequestException extends RuntimeException {

	private String messageKey;

	public BadRequestException(String messageKey) {
		super(messageKey);
		this.messageKey = messageKey;
	}

	public String getMessageKey() {
		return messageKey;
	}
}