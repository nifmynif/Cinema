package by.itacademy.exceptions;

public class NullTicketException extends IllegalArgumentException {

    public NullTicketException() {
    }

    public NullTicketException(String message) {
        super(message);
    }

    public NullTicketException(String message, Throwable cause) {
        super(message, cause);
    }
}
