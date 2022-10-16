package by.itacademy.servese;

import by.itacademy.constant.Constant;
import by.itacademy.d.*;
import by.itacademy.exceptions.MovieDaoException;
import by.itacademy.exceptions.UserDaoException;
import by.itacademy.model.Movie;
import by.itacademy.model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    private DateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT);
    private UserDao userDao = new UserDaoImpl();
    private MoviesDao moviesDao = new MoviesDaoImpl();
    TicketDao ticketDao = new TicketDaoImpl();
    private Calendar now = Calendar.getInstance();

    @Override
    public List viewMovies(User user) throws SQLException, IOException {
        fileRecord(user, " просмотрел все сеансы");
        return moviesDao.viewMovies();
    }

    @Override
    public boolean updateMovie(User user, Movie movie, Movie newMovie) throws IOException {
        try {
            movie.setId(moviesDao.getMovieId(movie.getName()));
            moviesDao.updateMovie(movie, newMovie);
        } catch (Exception e) {
            fileRecord(user, " фильм не обновлен " + e);
            throw new MovieDaoException("фильм не обновлен");
        }
        fileRecord(user, " обновил фильм " + movie.getName());
        return true;
    }

    @Override
    public boolean deleteMovie(User user, Movie movie) throws IOException {
        try {
            movie.setId(moviesDao.getMovieId(movie.getName()));
            ticketDao.deleteTicket(movie);
            moviesDao.deleteMovie(movie);
        } catch (Exception e) {
            fileRecord(user, " фильм не удален " + e);
            throw new MovieDaoException("фильм не удален");
        }
        fileRecord(user, " удалил фильм " + movie.getName());
        return true;
    }

    @Override
    public List viewUser(User user, String SQL) throws SQLException, IOException {
        fileRecord(user, " просмотрел всех пользователей");
        return userDao.viewUsers(SQL);
    }

    @Override
    public boolean updateUser(User user, User newUser) throws IOException {
        try {
            userDao.updateUser(newUser);
        } catch (Exception e) {
            fileRecord(user, newUser.getLogin() + " пользователь не изменен " + e);
            throw new UserDaoException(newUser.getLogin() + " пользователь не изменен");
        }
        fileRecord(user, newUser.getLogin() + " пользователь изменен ");
        return true;
    }

    @Override
    public boolean deleteUser(User user, User deletedUser) throws IOException {
        try {
            userDao.deleteUser(deletedUser);
        } catch (Exception e) {
            fileRecord(user, deletedUser.getLogin() + " пользователь не удален " + e);
            throw new UserDaoException(deletedUser.getLogin() + " пользователь не удален");
        }
        fileRecord(user, " удалил пользователя " + deletedUser.getLogin());
        return true;
    }

    @Override
    public boolean updateLogin(User user, String newName) throws SQLException, IOException {
        user.setLogin(newName);
        try {
            userDao.updateLogin(user);
        } catch (Exception e) {
            fileRecord(user, " логин не изменен " + e);
            throw new UserDaoException("логин не изменен");
        }
        fileRecord(user, " сменил логин на " + newName);
        return true;
    }

    @Override
    public boolean updatePassword(User user, String newPassword) throws SQLException, IOException, NoSuchAlgorithmException {
        user.setPassword(newPassword);
        try {
            userDao.updatePassword(user);
        } catch (Exception e) {
            fileRecord(user, " пароль не изменен " + e);
            throw new UserDaoException("пороль не изменен");
        }
        fileRecord(user, " сменил пароль");
        return true;
    }

    private void fileRecord(User user, String message) throws IOException {
        File file = new File
                (Constant.FILE_PATH);
        if (!file.exists())
            file.createNewFile();
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(dateFormat.format(now.getTime()) + " / " + user.getLogin() + " / " + message + "\n");
        fileWriter.close();
    }
}
