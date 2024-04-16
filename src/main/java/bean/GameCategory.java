package bean;

public class GameCategory {
    private int gameId;
    private int categoryId;

    public GameCategory() {
    }

    public GameCategory(int gameId, int categoryId) {
        this.gameId = gameId;
        this.categoryId = categoryId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
