package by.itacademy.d;

import by.itacademy.constant.Constant;
import by.itacademy.connection.AbstractConnection;
import by.itacademy.exceptions.NullUserException;
import by.itacademy.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public void chekUser(User user) throws SQLException, NullUserException {
        Connection conn = AbstractConnection.get();
        PreparedStatement stmt = conn.prepareStatement(Constant.CHEK_USER);
        stmt.setString(1, user.getLogin());
        stmt.setString(2, user.getPassword());
        ResultSet rs = stmt.executeQuery();
        if (!(rs.next()))
            throw new NullUserException("неверный логин или пароль");
        stmt.close();
        conn.close();
    }

    @Override
    public int getUserId(User user) throws SQLException {
        Connection conn = AbstractConnection.get();
        PreparedStatement stmt = conn.prepareStatement
                (Constant.GET_USER_ID);
        try {
            stmt.setString(1, String.valueOf(user.getLogin()));
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
    public String getRole(User user) throws SQLException {
        Connection conn = AbstractConnection.get();
        PreparedStatement stmt = conn.prepareStatement(Constant.GET_ROLE);
        String role = null;
        try {
            stmt.setString(1, user.getLogin());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                role = rs.getString(1);
            }
            return role;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
        return role;
    }

    @Override
    public String getLogin(int id) throws SQLException {
        Connection conn = AbstractConnection.get();
        PreparedStatement stmt = conn.prepareStatement(Constant.GET_LOGIN);
        String login = null;
        try {
            stmt.setString(1, String.valueOf(id));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                login = rs.getString(1);
            }
            return login;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
        return login;
    }

    @Override
    public String getPassword(User user) throws SQLException {
        Connection conn = AbstractConnection.get();
        PreparedStatement stmt = conn.prepareStatement(Constant.GET_PASSWORD);
        String password = null;
        try {
            stmt.setString(1, String.valueOf(user.getLogin()));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                password = rs.getString(1);
            }
            return password;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
        return password;
    }

    @Override
    public boolean updateLogin(User user) throws SQLException {
        Connection conn = AbstractConnection.get();
        PreparedStatement stmt = conn.prepareStatement(Constant.UPDATE_LOGIN);
        try {
            stmt.setString(1, user.getLogin());
            stmt.setString(2, String.valueOf(user.getId()));
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
    public boolean updatePassword(User user) throws SQLException {
        Connection conn = AbstractConnection.get();
        PreparedStatement stmt = conn.prepareStatement(Constant.UPDATE_PASSWORD);
        try {
            stmt.setString(1, user.getPassword());
            stmt.setString(2, String.valueOf(user.getId()));
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
    public boolean deleteUser(User user) throws SQLException {
        Connection conn = AbstractConnection.get();
        PreparedStatement stmt = conn.prepareStatement(Constant.DELETE_USER);
        try {
            stmt.setString(1, user.getLogin());
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
    public List viewUsers(String SQL) throws SQLException {
        Connection conn = AbstractConnection.get();
        List<User> users = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement(SQL);
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getInt(1)
                        , rs.getString(2)
                        , rs.getString(3)));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
        return null;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        Connection conn = AbstractConnection.get();
        PreparedStatement stmt = conn.prepareStatement(Constant.UPDATE_USER);
        try {
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.setString(4, String.valueOf(user.getId()));
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
    public boolean createUser(User user) throws SQLException {
        Connection conn = AbstractConnection.get();
        PreparedStatement stmt = conn.prepareStatement(Constant.CREATE_USER);
        try {
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
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
    public boolean findSameLogin(String name) throws SQLException {
        Connection conn = AbstractConnection.get();
        PreparedStatement stmt = conn.prepareStatement(Constant.FIND_SAME_LOGIN);
        try {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            stmt.close();
            conn.close();
        }
    }
}
