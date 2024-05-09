package br.com.escconsulting.exception.user;

public class UserEmailNotFoundException extends RuntimeException {

    public UserEmailNotFoundException() {
        super();
    }

    public UserEmailNotFoundException(String message) {
        super(message);
    }

    public UserEmailNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserEmailNotFoundException(Throwable cause) {
        super(cause);
    }
}