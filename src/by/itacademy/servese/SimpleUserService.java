package by.itacademy.servese;

import by.itacademy.exceptions.NullFilmException;
import by.itacademy.exceptions.NullTicketException;
import by.itacademy.model.Ticket;
import by.itacademy.model.User;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public interface SimpleUserService {

    boolean create(User user) throws SQLException, IOException;

    List viewMovies(User user) throws SQLException, IOException;

    boolean buyTicket(User user, Ticket ticket) throws SQLException, IOException, NullFilmException;

    boolean returnTicket(User user, Ticket ticket) throws SQLException, IOException, NullFilmException, NullTicketException;

    List viewMyTickets(User user) throws SQLException, IOException;

    boolean updateLogin(User user, String newName) throws SQLException, IOException;

    boolean updatePassword(User user, String newPassword) throws SQLException, IOException, NoSuchAlgorithmException;

    boolean delete(User user) throws SQLException, IOException;
}
