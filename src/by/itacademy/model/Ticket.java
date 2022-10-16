package by.itacademy.model;

public class Ticket {
    private int id;
    private User user;
    private int place;
    private float price;
    private Movie movie;

    public Ticket() {
    }

    public Ticket(User user, int place, Movie movie) {
        this.user = user;
        this.place = place;
        this.movie = movie;
    }

    public Ticket(User user, int place, float price, Movie movie) {
        this.user = user;
        this.place = place;
        this.price = price;
        this.movie = movie;
    }

    public Ticket(int place, float price, Movie movie) {
        this.place = place;
        this.price = price;
        this.movie = movie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
