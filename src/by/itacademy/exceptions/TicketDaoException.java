package by.itacademy.exceptions;

public class TicketDaoException extends IllegalArgumentException {

    public TicketDaoException() {
    }

    public TicketDaoException(String message) {
        super(message);
    }

    public TicketDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
