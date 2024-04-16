package bean;

public class GameDeveloper {
    private Game game;
    private Developer developer;

    public GameDeveloper() {
    }

    public GameDeveloper(Game game, Developer developer) {
        this.game = game;
        this.developer = developer;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }
}
