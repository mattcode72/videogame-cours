package bean;

public class GameCategory {
    private int game_id;
    private int category_id;

    public GameCategory() {
    }

    public GameCategory(int game_id, int category_id) {
        this.game_id = game_id;
        this.category_id = category_id;
    }

    public int getGame() {
        return game_id;
    }

    public void setGame(int game_id) {
        this.game_id = game_id;
    }

    public int getCategory() {
        return category_id;
    }

    public void setCategory(int category_id) {
        this.category_id = category_id;
    }
}
