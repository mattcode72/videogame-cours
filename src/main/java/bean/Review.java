package bean;

import java.util.Date;

public class Review {
    private int id;
    private int rating;
    private String content;
    private Date date;
    private Game game;
    private User user;

    public Review() {
    }

    public Review(int rating, String content, Date date, Game game, User user) {
        this.rating = rating;
        this.content = content;
        this.date = date;
        this.game = game;
        this.user = user;
    }

    public Review(int id, int rating, String content, Date date, Game game, User user) {
        this.id = id;
        this.rating = rating;
        this.content = content;
        this.date = date;
        this.game = game;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
