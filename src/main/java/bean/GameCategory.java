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

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
