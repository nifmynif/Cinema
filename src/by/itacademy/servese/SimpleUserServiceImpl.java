package by.itacademy.servese;

import by.itacademy.constant.Constant;
import by.itacademy.d.*;
import by.itacademy.exceptions.NullFilmException;
import by.itacademy.exceptions.NullTicketException;
import by.itacademy.exceptions.TicketDaoException;
import by.itacademy.exceptions.UserDaoException;
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

public class SimpleUserServiceImpl implements SimpleUserService {
    private DateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT);
    private UserDao userDao = new UserDaoImpl();
    private TicketDao ticketDao = new TicketDaoImpl();
    private MoviesDao moviesDao = new MoviesDaoImpl();
    private Calendar now = Calendar.getInstance();

    @Override
    public boolean create(User user) throws IOException {
        try {
            if (userDao.findSameLogin(user.getLogin()))
                throw new UserDaoException("пользователь с таким именем уже есть");
            userDao.createUser(user);
            user.setId(userDao.getUserId(user));
        } catch (UserDaoException e) {
            fileRecord(user, " пользователь не создан " + e);
            throw new UserDaoException("пользователь не создан");
        } catch (Exception e) {
            fileRecord(user, e.toString());
        }
        fileRecord(user, " пользователь создан");
        return true;
    }

    @Override
    public List viewMovies(User user) throws SQLException, IOException {
        fileRecord(user, " просмотрел все сеансы");
        return moviesDao.viewMovies();
    }

    @Override
    public boolean buyTicket(User user, Ticket ticket) throws IOException, NullFilmException {
        try {
            moviesDao.getMoviePremiere(ticket.getMovie().getName()).equals(ticket.getMovie().getDate());
            ticket.getMovie().setId(moviesDao.getMovieId(ticket.getMovie().getName()));
            ticketDao.buyTicket(ticket);
        } catch (NullFilmException e) {
            fileRecord(user, " нет такого фильма или такого сеанса " + e);
            throw new NullFilmException("нет такого фильма или такого сеанса");
        } catch (TicketDaoException e) {
            fileRecord(user, " ошибка покупки билета " + e);
            throw new TicketDaoException("ошибка покупки билета");
        } catch (Exception e) {
            fileRecord(user, e.toString());
        }
        fileRecord(user,
                " купил билет на фильм " + ticket.getMovie().getName() + " место " + ticket.getPlace());
        return true;
    }

    @Override
    public boolean returnTicket(User user, Ticket ticket) throws IOException, NullTicketException {
        try {
            moviesDao.getMoviePremiere(ticket.getMovie().getName()).equals(ticket.getMovie().getDate());
            ticket.getMovie().setId(moviesDao.getMovieId(ticket.getMovie().getName()));
            ticket.setId(ticketDao.getTicketId(ticket));
            ticketDao.returnTicket(ticket);
        } catch (NullTicketException e) {
            fileRecord(user, " нет такого билета " + e);
            throw new NullTicketException("нет такого билета");
        } catch (TicketDaoException e) {
            fileRecord(user, " ошибка возврата билета " + e);
            throw new TicketDaoException("ошибка возврата билета");
        } catch (Exception e) {
            fileRecord(user, e.toString());
        }
        fileRecord(user,
                " вернул билет на фильм " + ticket.getMovie().getName() + " место " + ticket.getPlace());
        return true;
    }

    @Override
    public List viewMyTickets(User user) throws SQLException, IOException {
        fileRecord(user, user.getLogin() + " просмотрел купленые билеты");
        return ticketDao.viewMyTickets(user);
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
