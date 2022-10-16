package by.itacademy.model;

import by.itacademy.d.MoviesDao;
import by.itacademy.d.MoviesDaoImpl;

import java.sql.SQLException;

public class Movie {
    private int id;
    private String name;
    private String date;
    private String placeAndPrice;
    private MoviesDao moviesDao = new MoviesDaoImpl();

    public Movie() {
    }

    public Movie(int id) throws SQLException {
        this.id = id;
        this.name = moviesDao.getMovieName(id);
        this.date = moviesDao.getMoviePremiere(this.name);
    }

    public Movie(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public String getPlaceAndPrice() {
        return placeAndPrice;
    }

    public void setPlaceAndPrice(String placeAndPrice) {
        this.placeAndPrice = placeAndPrice;
    }

    public MoviesDao getMoviesDao() {
        return moviesDao;
    }

    public void setMoviesDao(MoviesDao moviesDao) {
        this.moviesDao = moviesDao;
    }

    public Movie(String name, String date, String placeAndPrice) {
        this.name = name;
        this.date = date;
        this.placeAndPrice = placeAndPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
