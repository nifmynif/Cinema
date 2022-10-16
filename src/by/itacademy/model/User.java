package by.itacademy.model;

import by.itacademy.d.UserDao;
import by.itacademy.d.UserDaoImpl;
import by.itacademy.exceptions.NullUserException;
import by.itacademy.exceptions.UserDaoException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class User {
    private int id;
    private String login;
    private String password;
    private String role;
    private UserDao userDao = new UserDaoImpl();

    public User() {
    }

    public User(int id) throws SQLException {
        this.id = id;
        this.login = userDao.getLogin(id);
    }

    public User(String login) throws SQLException {
        this.login = login;
        this.id = userDao.getUserId(this);
        this.role=userDao.getRole(this);
    }

    public User(String login, String password) throws SQLException, NullUserException, NoSuchAlgorithmException {
        this.login = login;
        this.password = getHash(password);
        userDao.chekUser(this);
        this.id = userDao.getUserId(this);
        this.role = userDao.getRole(this);
    }

    public User(String login, String password, String role) throws NoSuchAlgorithmException, SQLException {
        if (userDao.findSameLogin(login))
            throw new UserDaoException("пользователь с таким именем уже есть");
        this.login = login;
        this.password = getHash(password);
        this.role = role;
    }

    public User(int id, String login, String role) {
        this.id = id;
        this.login = login;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) throws SQLException {
        if (userDao.findSameLogin(login))
            throw new UserDaoException("пользователь с таким именем уже есть");
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
        this.password = getHash(password);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String getHash(String password) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] bytes = md5.digest(password.getBytes());
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(String.format("%02X", b));
        }
        return stringBuilder.toString();
    }
}
