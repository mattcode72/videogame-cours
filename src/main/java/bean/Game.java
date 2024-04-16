package bean;

import dao.GameCategoryDAO;

import java.util.ArrayList;
import java.util.Date;

public class Game {
    private int id;
    private String name;
    private String description;
    private Date releaseDate;
    private int price;
    private ArrayList<GameCategory> categories;


    public Game() {
    }

    public Game(String name, String description, Date releaseDate, int price, ArrayList<GameCategory> categories) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.price = price;
        this.categories = categories;
    }

    public Game(int id, String name, String description, Date releaseDate, int price, ArrayList<GameCategory> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.price = price;
        this.categories = categories;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ArrayList<GameCategory> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<GameCategory> categories) {
        this.categories = categories;
    }
}