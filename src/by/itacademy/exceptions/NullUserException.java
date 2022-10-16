package by.itacademy.exceptions;

public class NullUserException extends IllegalArgumentException {

    public NullUserException() {
    }

    public NullUserException(String message) {
        super(message);
    }

    public NullUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
