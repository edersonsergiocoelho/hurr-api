package br.com.escconsulting.exception.user;

public class UserForgotPasswordValidatedException extends RuntimeException {

    public UserForgotPasswordValidatedException() {
        super();
    }

    public UserForgotPasswordValidatedException(String message) {
        super(message);
    }

    public UserForgotPasswordValidatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserForgotPasswordValidatedException(Throwable cause) {
        super(cause);
    }
}