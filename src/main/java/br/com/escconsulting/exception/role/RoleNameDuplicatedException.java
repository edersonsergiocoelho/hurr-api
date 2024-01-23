package br.com.escconsulting.exception.role;

public class RoleNameDuplicatedException extends RuntimeException {

    public RoleNameDuplicatedException() {
        super();
    }

    public RoleNameDuplicatedException(String message) {
        super(message);
    }

    public RoleNameDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleNameDuplicatedException(Throwable cause) {
        super(cause);
    }
}