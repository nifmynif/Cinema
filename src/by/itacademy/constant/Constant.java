package by.itacademy.constant;

public class Constant {
    public static final String GET_MOVIE_ID = "SELECT id FROM movies WHERE name = ?";
    public static final String GET_MOVIE_NAME = "SELECT name FROM movies WHERE id = ?";
    public static final String GET_MOVIE_PREMIERE = "SELECT premiere FROM movies WHERE name = ?";
    public static final String VIEW_MOVIES = "SELECT movies.name,\n" +
            "movies.premiere,\n" +
            "(SELECT GROUP_CONCAT(tickets.place SEPARATOR ', ') \n" +
            "FROM tickets \n" +
            "WHERE tickets.id_movie=movies.id AND tickets.exist='1'),\n" +
            "(SELECT GROUP_CONCAT(tickets.price SEPARATOR ', ') \n" +
            "FROM tickets \n" +
            "WHERE tickets.id_movie=movies.id AND tickets.exist='1')\n" +
            "FROM movies";
    public static final String UPDATE_MOVIE = "UPDATE `movies` SET `name`=?,`premiere`=? WHERE `id`=?";
    public static final String CREATE_MOVIE = "INSERT INTO movies (name, premiere) VALUES (?,?)";
    public static final String DELETE_MOVIE = "DELETE FROM movies WHERE id = ?";

    public static final String GET_TICKET_ID = "SELECT id FROM tickets WHERE id_user = ? AND id_movie=? AND place=?";
    public static final String VIEW_TICKETS = "SELECT `id_user`, `id_movie`, `place`, `price` FROM `tickets`";
    public static final String VIEW_ORDERED = "SELECT `id_user`, `id_movie`, `place`, `price` " +
            "FROM `tickets` WHERE exist=0";
    public static final String VIEW_NOT_ORDERED = "SELECT `id_user`, `id_movie`, `place`, `price` " +
            "FROM `tickets` WHERE exist=1";
    public static final String VIEW_MY_TICKETS = "SELECT `id_movie`, `place`, `price`" +
            "FROM `tickets` WHERE id_user=?";
    public static final String CREATE_TICKET = "INSERT INTO `tickets`( `id_movie`, `place`, `price`, `exist`) " +
            "VALUES (?,?,?,?)";
    public static final String BUY_TICKET = "UPDATE `tickets` SET `id_user` = ?, `exist` = ? " +
            "WHERE `tickets`.`place` = ? AND tickets.id_movie=? AND `tickets`.`exist` = ?";
    public static final String RETURN_TICKET = "UPDATE `tickets` SET `id_user` = ?, `exist` = ? " +
            "WHERE `tickets`.`place` = ? AND tickets.id_user=? AND `tickets`.`exist` = ?";
    public static final String DELETE_TICKET = "DELETE FROM `tickets` WHERE id_movie IN (?)";

    public static final String VIEW_USERS = "SELECT `id`, `login`,`role` FROM `users`";
    public static final String VIEW_NOT_ADMIN_USERS = "SELECT `id`, `login`, `role` FROM `users` " +
            "WHERE role = 'simple' OR role='manager'";
    public static final String CHEK_USER = "SELECT id FROM users WHERE login = ? AND password = ?";
    public static final String GET_USER_ID = "SELECT id FROM users WHERE login = ?";
    public static final String GET_ROLE = "SELECT role FROM users WHERE login = ?";
    public static final String GET_PASSWORD = "SELECT password FROM users WHERE login = ?";
    public static final String UPDATE_LOGIN = "UPDATE users SET login = ? WHERE id = ?";
    public static final String GET_LOGIN = "SELECT login FROM users WHERE id = ?";
    public static final String UPDATE_PASSWORD = "UPDATE users SET password = ? WHERE id = ?";
    public static final String DELETE_USER = "DELETE FROM users WHERE login = ?";
    public static final String CREATE_USER = "INSERT INTO users (login, password,role) VALUES (?,?,?)";
    public static final String UPDATE_USER = "UPDATE users SET login=?,password=?,role = ? WHERE id = ?";
    public static final String FIND_SAME_LOGIN = "SELECT login FROM users WHERE login = ?";

    public static final String FILE_PATH = "C:\\Users\\User\\IdeaProjects\\final_Nikita_Fedorov\\files\\logger";
    public static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
    public static final String URL = "jdbc:mysql://127.0.0.1:3334/cinema";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    public static final String DRIVER = "com.mysql.jdbc.Driver";
}
