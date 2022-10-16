package by.itacademy.exceptions;

public class UserDaoException extends IllegalArgumentException {

    public UserDaoException() {
    }

    public UserDaoException(String message) {
        super(message);
    }

    public UserDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
