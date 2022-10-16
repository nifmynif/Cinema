package by.itacademy.d;

import by.itacademy.constant.Constant;
import by.itacademy.connection.AbstractConnection;
import by.itacademy.model.Movie;
import by.itacademy.model.Ticket;
import by.itacademy.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDaoImpl implements TicketDao {
    @Override
    public int getTicketId(Ticket ticket) throws SQLException {
        Connection conn = AbstractConnection.get();
        PreparedStatement stmt = conn.prepareStatement(Constant.GET_TICKET_ID);
        try {
            stmt.setString(1, String.valueOf(ticket.getUser().getId()));
            stmt.setString(2, String.valueOf(ticket.getMovie().getId()));
            stmt.setString(3, String.valueOf(ticket.getPlace()));
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
    public boolean createTicket(Ticket ticket) throws SQLException {
        Connection conn = AbstractConnection.get();
        PreparedStatement stmt = conn.prepareStatement(Constant.CREATE_TICKET);
        try {
            stmt.setString(1, String.valueOf(ticket.getMovie().getId()));
            stmt.setString(2, String.valueOf(ticket.getPlace()));
            stmt.setString(3, String.valueOf(ticket.getPrice()));
            stmt.setString(4, "1");
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
    public boolean deleteTicket(Movie movie) throws SQLException {
        Connection conn = AbstractConnection.get();
        PreparedStatement stmt = conn.prepareStatement(Constant.DELETE_TICKET);
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

    @Override
    public boolean buyTicket(Ticket ticket) throws SQLException {
        Connection conn = AbstractConnection.get();
        PreparedStatement stmt = conn.prepareStatement(Constant.BUY_TICKET);
        try {
            stmt.setString(1, String.valueOf(ticket.getUser().getId()));
            stmt.setString(2, "0");
            stmt.setString(3, String.valueOf(ticket.getPlace()));
            stmt.setString(4, String.valueOf(ticket.getMovie().getId()));
            stmt.setString(5, "1");
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
    public boolean returnTicket(Ticket ticket) throws SQLException {
        Connection conn = AbstractConnection.get();
        PreparedStatement stmt = conn.prepareStatement(Constant.RETURN_TICKET);
        try {
            stmt.setString(1, null);
            stmt.setString(2, "1");
            stmt.setString(3, String.valueOf(ticket.getPlace()));
            stmt.setString(4, String.valueOf(ticket.getUser().getId()));
            stmt.setString(5, "0");
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
    public List viewMyTickets(User user) throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        Connection conn = AbstractConnection.get();
        PreparedStatement stmt = conn.prepareStatement(Constant.VIEW_MY_TICKETS);
        try {
            stmt.setString(1, Integer.toString(user.getId()));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tickets.add(new Ticket(user,
                        rs.getInt(2),
                        rs.getFloat(3),
                        new Movie(rs.getInt(1))));
            }
            return tickets;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
        return null;
    }

    @Override
    public List viewTickets(User user, String SQL) throws SQLException {
        Connection conn = AbstractConnection.get();
        List<Ticket> tickets = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement(SQL);
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tickets.add(new Ticket(
                        new User(rs.getInt(1)),
                        rs.getInt(3),
                        rs.getFloat(4),
                        new Movie(rs.getInt(2))));
            }
            return tickets;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
        return null;
    }
}
