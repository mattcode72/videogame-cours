package bean;

public class GameCategory {
    private Game game;
    private Category category;

    public GameCategory() {
    }

    public GameCategory(Game game, Category category) {
        this.game = game;
        this.category = category;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
