package bean;

public class GameTag {
    private Game game;

    private Tag tag;

    public GameTag() {
    }

    public GameTag(Game game, Tag tag) {
        this.game = game;
        this.tag = tag;
    }

    public GameTag(Tag tag) {
        this.tag = tag;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
