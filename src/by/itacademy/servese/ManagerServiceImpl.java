package by.itacademy.servese;

import by.itacademy.constant.Constant;
import by.itacademy.d.*;
import by.itacademy.exceptions.MovieDaoException;
import by.itacademy.exceptions.NullTicketException;
import by.itacademy.exceptions.TicketDaoException;
import by.itacademy.exceptions.UserDaoException;
import by.itacademy.model.Movie;
import by.itacademy.model.Ticket;
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

public class ManagerServiceImpl implements ManagerService {
    private DateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT);
    private UserDao userDao = new UserDaoImpl();
    private TicketDao ticketDao = new TicketDaoImpl();
    private MoviesDao moviesDao = new MoviesDaoImpl();
    private Calendar now = Calendar.getInstance();

    @Override
    public List viewMovies(User user) throws SQLException, IOException {
        fileRecord(user, " просмотрел все сеансы");
        return moviesDao.viewMovies();
    }

    @Override
    public boolean createMovie(User user, Movie movie, int numberOfTickets, float priceOfTicket) throws IOException {
        try {
            moviesDao.createMovie(user, movie);
            movie.setId(moviesDao.getMovieId(movie.getName()));
            createTickets(user, movie, numberOfTickets, priceOfTicket);
        } catch (Exception e) {
            fileRecord(user, " фильм не создан ");
            throw new MovieDaoException("фильм не создан");
        }
        fileRecord(user, " создан фильм " + movie.getName());
        return true;
    }

    private void createTickets(User user, Movie movie, int numberOfTickets, float priceOfTicket) throws IOException {
        try {
            for (int i = 1; i <= numberOfTickets; i++)
                ticketDao.createTicket(new Ticket(i, priceOfTicket, movie));
        } catch (Exception e) {
            throw new NullTicketException("бидет не создан");
        }
        fileRecord(user, " создал билеты к фильму = " + numberOfTickets);
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
    public List viewTickets(User user, String SQL) throws SQLException, IOException {
        fileRecord(user, " просмотрел все билеты");
        return ticketDao.viewTickets(user, SQL);
    }

    @Override
    public boolean buyTicket(User user, Ticket ticket) throws IOException {
        try {
            ticketDao.buyTicket(ticket);
        } catch (Exception e) {
            fileRecord(user, " билет не куплен " + e);
            throw new TicketDaoException("билет не куплен");
        }
        fileRecord(user, " купил билет для " + ticket.getUser().getLogin());
        return true;
    }

    @Override
    public boolean returnTicket(User user, Ticket ticket) throws IOException {
        try {
            ticketDao.returnTicket(ticket);
        } catch (Exception e) {
            fileRecord(user, " ошибка возврата билета " + e);
            throw new TicketDaoException("билет не удален");
        }
        fileRecord(user, " удалил билет для " + ticket.getUser().getLogin());
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
    public boolean updatePassword(User user, String newPassword) throws IOException, NoSuchAlgorithmException {
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

    @Override
    public boolean delete(User user) throws IOException {
        try {
            userDao.deleteUser(user);
        } catch (Exception e) {
            fileRecord(user, " пользователь не удален " + e);
            throw new UserDaoException("пользователь не удален");
        }
        fileRecord(user, " удалил аккаунт");
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
