package bean;

import java.util.Date;

public class Game {
    private int id;
    private String name;
    private String description;
    private Date releaseDate;
    private Lang lang;
    private Platform platform;
    private Category category;
    private GameMode gameMode;
    private Tag tag;
    private Developer developer;

    public Game() {
    }

    public Game(String name, String description, Date releaseDate, Lang lang, Platform platform, Category category, GameMode gameMode, Tag tag, Developer developer) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.lang = lang;
        this.platform = platform;
        this.category = category;
        this.gameMode = gameMode;
        this.tag = tag;
        this.developer = developer;
    }

    public Game(int id, String name, String description, Date releaseDate, Lang lang, Platform platform, Category category, GameMode gameMode, Tag tag, Developer developer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.lang = lang;
        this.platform = platform;
        this.category = category;
        this.gameMode = gameMode;
        this.tag = tag;
        this.developer = developer;
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

    public Lang getLang() {
        return lang;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }
}
