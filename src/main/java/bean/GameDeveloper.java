package bean;

public class GameDeveloper {
    private int gameId;
    private int developerId;

    public GameDeveloper() {
    }

    public GameDeveloper(int game_id, int developer_id) {
        this.gameId = game_id;
        this.developerId = developer_id;
    }

    public int getGame_id() {
        return gameId;
    }

    public void setGame_id(int gameId) {
        this.gameId = gameId;
    }

    public int getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(int developerId) {
        this.developerId = developerId;
    }
}
