package by.itacademy.exceptions;

public class NullFilmException extends IllegalArgumentException {

    public NullFilmException() {
    }

    public NullFilmException(String message) {
        super(message);
    }

    public NullFilmException(String message, Throwable cause) {
        super(message, cause);
    }
}
