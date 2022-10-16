package by.itacademy.d;

import by.itacademy.model.Movie;
import by.itacademy.model.Ticket;
import by.itacademy.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface TicketDao {
    int getTicketId(Ticket ticket) throws SQLException;

    List viewTickets(User user, String SQL) throws SQLException;

    boolean createTicket(Ticket ticket) throws SQLException, IOException;

    boolean deleteTicket(Movie movie) throws SQLException, IOException;

    boolean buyTicket(Ticket ticket) throws SQLException;

    boolean returnTicket(Ticket ticket) throws SQLException;

    List viewMyTickets(User user) throws SQLException;
}
