package by.itacademy.servese;

import by.itacademy.model.Movie;
import by.itacademy.model.User;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public interface AdminService {
    List viewMovies(User user) throws SQLException, IOException;

    boolean updateMovie(User user, Movie movie, Movie newMovie) throws SQLException, IOException;

    boolean deleteMovie(User user, Movie movie) throws SQLException, IOException;

    List viewUser(User user, String SQL) throws SQLException, IOException;

    boolean updateUser(User user, User newUser) throws SQLException, IOException;

    boolean deleteUser(User user, User deletedUser) throws SQLException, IOException;

    boolean updateLogin(User user, String newName) throws SQLException, IOException;

    boolean updatePassword(User user, String newPassword) throws SQLException, IOException, NoSuchAlgorithmException;
}
