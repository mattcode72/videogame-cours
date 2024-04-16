package bean;

public class GameGameMode {
    private int gameId;
    private int gameModeId;

    public GameGameMode() {
    }

    public GameGameMode(int game_id, int gameMode_id) {
        this.gameId = game_id;
        this.gameModeId = gameMode_id;
    }

    public int getGame_id() {
        return gameId;
    }

    public void setGame_id(int gameId) {
        this.gameId = gameId;
    }

    public int getGameModeId() {
        return gameModeId;
    }

    public void setGameModeId(int gameModeId) {
        this.gameModeId = gameModeId;
    }
}
