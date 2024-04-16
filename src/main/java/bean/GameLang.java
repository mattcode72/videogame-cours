package bean;

public class GameLang {
    private Game game;
    private Lang lang;

    public GameLang() {
    }

    public GameLang(Game game, Lang lang) {
        super();
        this.game = game;
        this.lang = lang;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Lang getLang() {
        return lang;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
    }
}
