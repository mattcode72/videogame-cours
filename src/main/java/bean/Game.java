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
    private ArrayList<Category> categories;
    private Media thumbnail;
    private ArrayList<Media> images;
    private ArrayList<Media> videos;
    private ArrayList<Platform> platforms;
    private ArrayList<Lang> langs;
    private ArrayList<GameMode> gameModes;
    private ArrayList<Developer> developers;
    private ArrayList<Review> reviews;


    public Game() {
    }

    public Game(String name, String description, Date releaseDate, int price, ArrayList<Category> categories, Media thumbnail) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.price = price;
        this.categories = categories;
        this.thumbnail = thumbnail;
    }

    public Game(int id, String name, String description, Date releaseDate, int price, ArrayList<Category> categories, ArrayList<Platform> platforms, ArrayList<Lang> langs, Media thumbnail) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.price = price;
        this.categories = categories;
        this.platforms = platforms;
        this.langs = langs;
        this.thumbnail = thumbnail;
    }

    public Game(int id,String name, String description, Date releaseDate, int price, ArrayList<Category> categories, ArrayList<Media> images, ArrayList<Media> videos, ArrayList<Platform> platforms, ArrayList<Lang> langs, ArrayList<GameMode> gameModes, ArrayList<Developer> developers, ArrayList<Review> reviews) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.price = price;
        this.categories = categories;
        this.images = images;
        this.videos = videos;
        this.platforms = platforms;
        this.langs = langs;
        this.gameModes = gameModes;
        this.developers = developers;
        this.reviews = reviews;
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

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public Media getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Media thumbnail) {
        this.thumbnail = thumbnail;
    }

    public ArrayList<Media> getImages() {
        return images;
    }

    public void setImages(ArrayList<Media> images) {
        this.images = images;
    }

    public ArrayList<Media> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<Media> videos) {
        this.videos = videos;
    }

    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(ArrayList<Platform> platforms) {
        this.platforms = platforms;
    }

    public ArrayList<Lang> getLangs() {
        return langs;
    }

    public void setLangs(ArrayList<Lang> langs) {
        this.langs = langs;
    }

    public ArrayList<GameMode> getGameModes() {
        return gameModes;
    }

    public void setGameModes(ArrayList<GameMode> gameModes) {
        this.gameModes = gameModes;
    }

    public ArrayList<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(ArrayList<Developer> developers) {
        this.developers = developers;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }
}