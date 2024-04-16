package bean;

import java.util.Date;

public class Order {
    private int id;
    private Date date;
    private User user;

    public Order() {
    }

    public Order(Date date, User user) {
        this.date = date;
        this.user = user;
    }

    public Order(int id, Date date, User user) {
        this.id = id;
        this.date = date;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
