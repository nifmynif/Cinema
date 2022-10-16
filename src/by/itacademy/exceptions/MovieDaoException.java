package by.itacademy.exceptions;

public class MovieDaoException extends IllegalArgumentException {

    public MovieDaoException() {
    }

    public MovieDaoException(String message) {
        super(message);
    }

    public MovieDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
