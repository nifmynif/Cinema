package by.itacademy.d;

import by.itacademy.exceptions.NullUserException;
import by.itacademy.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void chekUser(User user) throws SQLException, NullUserException;

    int getUserId(User user) throws SQLException;

    String getRole(User user) throws SQLException;

    String getLogin(int id) throws SQLException;

    String getPassword(User user) throws SQLException;

    boolean updateLogin(User user) throws SQLException, IOException;

    boolean updatePassword(User user) throws SQLException, IOException;

    List viewUsers(String SQL) throws SQLException;

    boolean updateUser(User user) throws SQLException;

    boolean deleteUser(User user) throws SQLException, IOException;

    boolean createUser(User user) throws SQLException;

    boolean findSameLogin(String name) throws SQLException;
}
