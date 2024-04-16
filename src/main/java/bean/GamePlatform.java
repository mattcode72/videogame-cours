package bean;

public class GamePlatform {
    private Game game;
    private Platform platform;

    public GamePlatform() {
    }

    public GamePlatform(Game game, Platform platform) {
        super();
        this.game = game;
        this.platform = platform;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }
}
