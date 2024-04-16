package bean;

public class GameGameMode {
    private Game game;
    private GameMode gameMode;

    public GameGameMode() {
    }

    public GameGameMode(Game game, GameMode gameMode) {
        this.game = game;
        this.gameMode = gameMode;
    }

    public GameGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }
}
