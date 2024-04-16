package bean;

public class GameCategory {
    private int gameId;
    private int categoryId;

    public GameCategory() {
    }

    public GameCategory(int game_id, int category_id) {
        this.gameId = game_id;
        this.categoryId = category_id;
    }

    public int getGame_id() {
        return gameId;
    }

    public void setGame_id(int gameId) {
        this.gameId = gameId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
