package by.itacademy.d;

import by.itacademy.model.Movie;
import by.itacademy.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface MoviesDao {
    int getMovieId(String name) throws SQLException;

    String getMovieName(int id) throws SQLException;

    String getMoviePremiere(String name) throws SQLException;

    List viewMovies() throws SQLException;

    boolean updateMovie(Movie movie, Movie newMovie) throws SQLException, IOException;

    boolean createMovie(User user, Movie movie) throws SQLException, IOException;

    boolean deleteMovie(Movie movie) throws SQLException;
}
