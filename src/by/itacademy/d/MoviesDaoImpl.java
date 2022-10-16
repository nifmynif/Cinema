package by.itacademy.d;

import by.itacademy.constant.Constant;
import by.itacademy.connection.AbstractConnection;
import by.itacademy.model.Movie;
import by.itacademy.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoviesDaoImpl implements MoviesDao {
    @Override
    public int getMovieId(String name) throws SQLException {
        Connection conn = AbstractConnection.get();
        PreparedStatement stmt = conn.prepareStatement(Constant.GET_MOVIE_ID);
        try {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                return rs.getInt(1);
            else return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            stmt.close();
            conn.close();
        }
    }

    @Override
    public String getMovieName(int id) throws SQLException {
        Connection conn = AbstractConnection.get();
        PreparedStatement stmt = conn.prepareStatement(Constant.GET_MOVIE_NAME);
        try {
            stmt.setString(1, String.valueOf(id));
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                return String.valueOf(rs.getString(1));
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            stmt.close();
            conn.close();
        }
    }

    @Override
    public String getMoviePremiere(String name) throws SQLException {
        Connection conn = AbstractConnection.get();
        PreparedStatement stmt = conn.prepareStatement(Constant.GET_MOVIE_PREMIERE);
        try {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                return rs.getString(1);
            else return "";
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        } finally {
            stmt.close();
            conn.close();
        }
    }

    @Override
    public List viewMovies() throws SQLException {
        List<Movie> movies = new ArrayList<>();
        Connection conn = AbstractConnection.get();
        PreparedStatement stmt = conn.prepareStatement(Constant.VIEW_MOVIES);
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                movies.add(new Movie(rs.getString(1),
                        rs.getString(2),
                        " место: " + rs.getString(3)
                                + " цена: " + rs.getString(4)));
            }
            return movies;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
        return null;
    }

    @Override
    public boolean updateMovie(Movie movie, Movie newMovie) throws SQLException {
        Connection conn = AbstractConnection.get();
        PreparedStatement stmt = conn.prepareStatement(Constant.UPDATE_MOVIE);
        try {
            stmt.setString(1, String.valueOf(newMovie.getName()));
            stmt.setString(2, newMovie.getDate());
            stmt.setString(3, String.valueOf(movie.getId()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        } finally {
            stmt.close();
            conn.close();
        }
    }

    @Override
    public boolean createMovie(User user, Movie movie) throws SQLException {
        Connection conn = AbstractConnection.get();
        PreparedStatement stmt = conn.prepareStatement(Constant.CREATE_MOVIE);
        try {
            stmt.setString(1, movie.getName());
            stmt.setString(2, movie.getDate());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            stmt.close();
            conn.close();
        }
    }

    @Override
    public boolean deleteMovie(Movie movie) throws SQLException {
        Connection conn = AbstractConnection.get();
        PreparedStatement stmt = conn.prepareStatement(Constant.DELETE_MOVIE);
        try {
            stmt.setString(1, String.valueOf(movie.getId()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        } finally {
            stmt.close();
            conn.close();
        }
    }
}
