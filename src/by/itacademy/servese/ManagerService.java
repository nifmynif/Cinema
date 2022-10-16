package by.itacademy.servese;

import by.itacademy.model.Movie;
import by.itacademy.model.Ticket;
import by.itacademy.model.User;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public interface ManagerService {
    List viewMovies(User user) throws SQLException, IOException;

    boolean createMovie(User user, Movie movie, int numberOfTickets, float priceOfTicket) throws SQLException, IOException;

    boolean updateMovie(User user, Movie movie, Movie newMovie) throws SQLException, IOException;

    List viewTickets(User user, String SQL) throws SQLException, IOException;

    boolean buyTicket(User user, Ticket ticket) throws SQLException, IOException;

    boolean returnTicket(User user, Ticket ticket) throws SQLException, IOException;

    boolean updateLogin(User user, String newName) throws SQLException, IOException;

    boolean updatePassword(User user, String newPassword) throws SQLException, IOException, NoSuchAlgorithmException;

    boolean delete(User user) throws SQLException, IOException;
}
