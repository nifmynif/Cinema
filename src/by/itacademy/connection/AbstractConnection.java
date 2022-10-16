package by.itacademy.connection;

import by.itacademy.constant.Constant;

import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractConnection {

    private static java.sql.Connection connection;

    public static java.sql.Connection get() throws SQLException {
        try {
            Class.forName(Constant.DRIVER);
            connection = DriverManager.getConnection(Constant.URL, Constant.USERNAME, Constant.PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("драйвер не установлен", e);
        }
        return connection;
    }

    public static void close() {
        try {
            connection.close();
            System.out.println("соединение разъеденино");
        } catch (SQLException e) {
            System.out.println("ошибка разъединнения");
        }
    }
}
